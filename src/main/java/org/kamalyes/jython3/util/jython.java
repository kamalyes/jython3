// Copyright (c) Corporation for National Research Initiatives
package org.kamalyes.jython3.util;

import org.kamalyes.jython3.core.CodeFlag;
import org.kamalyes.jython3.core.CompileMode;
import org.kamalyes.jython3.core.Options;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyCode;
import org.kamalyes.jython3.core.PyException;
import org.kamalyes.jython3.core.PyFile;
import org.kamalyes.jython3.core.PyList;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyStringMap;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.core.imp;
import org.kamalyes.jython3.core.util.RelativeFile;
import org.kamalyes.jython3.modules._systemrestart;
import org.kamalyes.jython3.modules.posix.PosixModule;
import org.kamalyes.jython3.modules.thread._thread;
import org.kamalyes.jython3.Version;
import org.kamalyes.jython3.core.PySystemState;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class jython {

  // An instance of this class will provide the console (python.console) by
  // default.
  private static final String PYTHON_CONSOLE_CLASS = "org.kamalyes.jython3.util.JLineConsole";

  private static final String COPYRIGHT = "Type \"help\", \"copyright\", \"credits\" or \"license\" for more information.";

  static final String usageHeader = "usage: jython [option] ... [-c cmd | -m mod | file | -] [arg] ...\n";

  private static final String usage = usageHeader
      + "Options and arguments:\n"
      // + "(and corresponding environment variables):\n"
      + "-B       : don't write bytecode files on import\n"
      // + "also PYTHONDONTWRITEBYTECODE=x\n" +
      + "-c cmd   : program passed in as string (terminates option list)\n"
      // + "-d : debug output from parser (also PYTHONDEBUG=x)\n"
      + "-Dprop=v : Set the property `prop' to value `v'\n"
      + "-E       : ignore environment variables (such as PYTHONPATH)\n"
      + "-h       : print this help message and exit (also --help)\n"
      + "-i       : inspect interactively after running script\n"
      // + ", (also PYTHONINSPECT=x)\n"
      + "           and force prompts, even if stdin does not appear to be a terminal\n"
      + "-jar jar : program read from __run__.py in jar file\n"
      + "-m mod   : run library module as a script (terminates option list)\n"
      // + "-O : optimize generated bytecode (a tad; also PYTHONOPTIMIZE=x)\n"
      // + "-OO : remove doc-strings in addition to the -O optimizations\n"
      + "-Q arg   : division options: -Qold (default), -Qwarn, -Qwarnall, -Qnew\n"
      + "-s       : don't add user site directory to sys.path;\n"
      // + "also PYTHONNOUSERSITE\n"
      + "-S       : don't imply 'import site' on initialization\n"
      // + "-t : issue warnings about inconsistent tab usage (-tt: issue errors)\n"
      + "-u       : unbuffered binary stdout and stderr\n"
      // + "(also PYTHONUNBUFFERED=x)\n"
      // + " see man page for details on internal buffering relating to '-u'\n"
      + "-v       : verbose (trace import statements)\n"
      // + "(also PYTHONVERBOSE=x)\n"
      + "           can be supplied multiple times to increase verbosity\n"
      + "-V       : print the Python version number and exit (also --version)\n"
      + "-W arg   : warning control (arg is action:message:category:module:lineno)\n"
      // + "-x : skip first line of source, allowing use of non-Unix forms of #!cmd\n"
      + "-3       : warn about Python 3.x incompatibilities that 2to3 cannot trivially fix\n"
      + "file     : program read from script file\n"
      + "-        : program read from stdin (default; interactive mode if a tty)\n"
      + "arg ...  : arguments passed to program in sys.argv[1:]\n" + "\n"
      + "Other environment variables:\n" //
      + "PYTHONPATH: '" + File.pathSeparator
      + "'-separated list of directories prefixed to the default module\n"
      + "            search path.  The result is sys.path.\n"
      + "PYTHONIOENCODING: Encoding[:errors] used for stdin/stdout/stderr.";

  public static boolean shouldRestart;

  /**
   * Runs a JAR file, by executing the code found in the file __run__.py, which
   * should be in the
   * root of the JAR archive. Note that the __name__ is set to the base name of
   * the JAR file and
   * not to "__main__" (for historic reasons). This method do NOT handle
   * Exceptions. the caller
   * SHOULD handle any (Py)Exceptions thrown by the code.
   *
   * @param filename The path to the filename to run.
   */
  public static void runJar(String filename) {
    // TBD: this is kind of gross because a local called `zipfile' just magically
    // shows up in the module's globals. Either `zipfile' should be called
    // `__zipfile__' or (preferably, IMO), __run__.py should be imported and a
    // main()
    // function extracted. This function should be called passing zipfile in as an
    // argument.
    //
    // Probably have to keep this code around for backwards compatibility (?)
    try {
      ZipFile zip = new ZipFile(filename);

      ZipEntry runit = zip.getEntry("__run__.py");
      if (runit == null) {
        throw Py.ValueError("jar file missing '__run__.py'");
      }

      PyStringMap locals = Py.newStringMap();

      // Stripping the stuff before the last File.separator fixes Bug #931129 by
      // keeping illegal characters out of the generated proxy class name
      int beginIndex;
      if ((beginIndex = filename.lastIndexOf(File.separator)) != -1) {
        filename = filename.substring(beginIndex + 1);
      }

      locals.__setitem__("__name__", new PyUnicode(filename));
      locals.__setitem__("zipfile", Py.java2py(zip));

      InputStream file = zip.getInputStream(runit);
      PyCode code;
      try {
        code = Py.compile(file, "__run__", CompileMode.exec);
      } finally {
        file.close();
      }
      Py.runCode(code, locals, locals);
    } catch (IOException e) {
      throw Py.IOError(e);
    }
  }

  public static void main(String[] args) {
    do {
      shouldRestart = false;
      run(args);
    } while (shouldRestart);
  }

  private static List<String> warnOptionsFromEnv() {
    ArrayList<String> opts = new ArrayList<String>();

    try {
      String envVar = System.getenv("PYTHONWARNINGS");
      if (envVar == null) {
        return opts;
      }

      for (String opt : envVar.split(",")) {
        opt = opt.trim();
        if (opt.length() == 0) {
          continue;
        }
        opts.add(opt);
      }
    } catch (SecurityException e) {
      // Continue
    }

    return opts;
  }

  private static List<String> validWarnActions = Arrays.asList("error", "ignore", "always",
      "default", "module", "once");

  private static void addWarnings(List<String> from, PyList to) {
    outerLoop: for (String opt : from) {
      String action = opt.split(":")[0];
      for (String validWarnAction : validWarnActions) {
        if (validWarnAction.startsWith(action)) {
          if (opt.contains(":")) {
            to.append(Py.newString(validWarnAction + opt.substring(opt.indexOf(":"))));
          } else {
            to.append(Py.newString(validWarnAction));
          }
          continue outerLoop;
        }
      }
      System.err.println(String.format("Invalid -W option ignored: invalid action: '%s'",
          action));
    }
  }

  private static boolean runModule(InteractiveConsole interp, String moduleName) {
    return runModule(interp, moduleName, true);
  }

  private static boolean runModule(InteractiveConsole interp, String moduleName, boolean set_argv0) {
    // PEP 338 - Execute module as a script
    PyObject runpy = imp.importName("runpy", true);
    PyObject runmodule = runpy.__findattr__("_run_module_as_main");
    runmodule.__call__(Py.newUnicode(moduleName), Py.newBoolean(set_argv0));
    return true;
  }

  private static boolean runMainFromImporter(InteractiveConsole interp, String filename) {
    // Support http://bugs.python.org/issue1739468 - Allow interpreter to execute a
    // zip file or directory
    PyUnicode argv0 = Py.newUnicode(filename);
    Py.getSystemState().path.insert(0, argv0);
    try {
      return runModule(interp, "__main__", false);
    } catch (Throwable t) {
      return false;
    }
  }

  public static void run(String[] args) {
    // Parse the command line options
    CommandLineOptions opts = new CommandLineOptions();
    if (!opts.parse(args)) {
      if (opts.version) {
        System.err.println("Jython " + Version.PY_VERSION);
        System.exit(0);
      }
      if (opts.help) {
        System.err.println(usage);
      } else if (!opts.runCommand && !opts.runModule) {
        System.err.print(usageHeader);
        System.err.println("Try `jython -h' for more information.");
      }

      int exitcode = opts.help ? 0 : -1;
      System.exit(exitcode);
    }

    // Get system properties (or empty set if we're prevented from accessing them)
    Properties preProperties = PySystemState.getBaseProperties();

    // Read environment variable PYTHONIOENCODING into properties (registry)
    String pythonIoEncoding = getenv("PYTHONIOENCODING");
    if (pythonIoEncoding != null) {
      String[] spec = splitString(pythonIoEncoding, ':', 2);
      // Note that if encoding or errors is blank (=null), the registry value wins.
      addDefault(preProperties, PySystemState.PYTHON_IO_ENCODING, spec[0]);
      addDefault(preProperties, PySystemState.PYTHON_IO_ERRORS, spec[1]);
    }

    // Decide if System.in is interactive
    if (!opts.fixInteractive || opts.interactive) {
      // The options suggest System.in is interactive: but only if isatty() agrees
      opts.interactive = Py.isInteractive();
      if (opts.interactive) {
        // Set the default console type if nothing else has
        addDefault(preProperties, "python.console", PYTHON_CONSOLE_CLASS);
      }
    }

    // Setup the basic python system state from these options
    PySystemState.initialize(preProperties, opts.properties, opts.argv);
    PySystemState systemState = Py.getSystemState();

    PyList warnoptions = new PyList();
    addWarnings(opts.warnoptions, warnoptions);
    if (!Options.ignore_environment) {
      addWarnings(warnOptionsFromEnv(), warnoptions);
    }
    systemState.setWarnoptions(warnoptions);

    // Make sure warnings module is loaded if there are warning options
    // Not sure this is needed, but test_warnings.py expects this
    if (warnoptions.size() > 0) {
      imp.load("warnings");
    }

    // Now create an interpreter
    if (!opts.interactive) {
      // Not (really) interactive, so do not use console prompts
      systemState.ps1 = systemState.ps2 = Py.EmptyUnicode;
    }
    InteractiveConsole interp = new InteractiveConsole();

    // Print banner and copyright information (or not)
    if (opts.interactive && opts.notice && !opts.runModule) {
      System.err.println(InteractiveConsole.getDefaultBanner());
    }

    if (Py.importSiteIfSelected()) {
      if (opts.interactive && opts.notice && !opts.runModule) {
        System.err.println(COPYRIGHT);
      }
    }

    if (opts.division != null) {
      if ("old".equals(opts.division)) {
        Options.division_warning = 0;
      } else if ("warn".equals(opts.division)) {
        Options.division_warning = 1;
      } else if ("warnall".equals(opts.division)) {
        Options.division_warning = 2;
      } else if ("new".equals(opts.division)) {
        Options.Qnew = true;
        // interp.cflags.setFlag(CodeFlag.CO_FUTURE_DIVISION);
      }
    }

    // was there a filename on the command line?
    if (opts.filename != null) {
      if (runMainFromImporter(interp, opts.filename)) {
        interp.cleanup();
        return;
      }

      String path;
      try {
        path = new File(opts.filename).getCanonicalFile().getParent();
      } catch (IOException ioe) {
        path = new File(opts.filename).getAbsoluteFile().getParent();
      }
      if (path == null) {
        path = "";
      }
      Py.getSystemState().path.insert(0, Py.newUnicode(path));
      if (opts.jar) {
        try {
          runJar(opts.filename);
        } catch (Throwable t) {
          Py.printException(t);
          System.exit(-1);
        }
      } else if (opts.filename.equals("-")) {
        try {
          interp.globals.__setitem__(new PyUnicode("__file__"), new PyUnicode("<stdin>"));
          interp.execfile(System.in, "<stdin>");
        } catch (Throwable t) {
          Py.printException(t);
        }
      } else {
        try {
          interp.globals.__setitem__(new PyUnicode("__file__"),
              new PyUnicode(opts.filename));

          FileInputStream file;
          try {
            file = new FileInputStream(new RelativeFile(opts.filename));
          } catch (FileNotFoundException e) {
            throw Py.IOError(e);
          }
          try {
            boolean isInteractive = false;
            try {
              isInteractive = PosixModule.getPOSIX().isatty(file.getFD());
            } catch (SecurityException ex) {
            }
            if (isInteractive) {
              opts.interactive = true;
              interp.interact(null, new PyFile(file));
              return;
            } else {
              interp.execfile(file, opts.filename);
            }
          } finally {
            file.close();
          }
        } catch (Throwable t) {
          if (t instanceof PyException
              && ((PyException) t).match(_systemrestart.SystemRestart)) {
            // Shutdown this instance...
            shouldRestart = true;
            shutdownInterpreter();
            interp.cleanup();
            // ..reset the state...
            Py.setSystemState(new PySystemState());
            // ...and start again
            return;
          } else {
            Py.printException(t);
            interp.cleanup();
            System.exit(-1);
          }
        }
      }
    } else {
      // if there was no file name on the command line, then "" is the first element
      // on sys.path. This is here because if there /was/ a filename on the c.l.,
      // and say the -i option was given, sys.path[0] will have gotten filled in
      // with the dir of the argument filename.
      Py.getSystemState().path.insert(0, Py.EmptyUnicode);

      if (opts.command != null) {
        try {
          interp.exec(opts.command);
        } catch (Throwable t) {
          Py.printException(t);
          System.exit(1);
        }
      }

      if (opts.moduleName != null) {
        try {
          runModule(interp, opts.moduleName);
        } catch (Throwable t) {
          Py.printException(t);
          System.exit(1);
        } finally {
          interp.cleanup();
        }
        return;
      }
    }

    if (opts.fixInteractive || (opts.filename == null && opts.command == null)) {
      // Go interactive with the console: the parser needs to know the encoding.
      String encoding = Py.getConsole().getEncoding();
      // Run the interpreter interactively
      try {
        interp.cflags.encoding = encoding;
        if (!opts.interactive) {
          // Don't print prompts. http://bugs.jython.org/issue2325
          interp._interact(null, null);
        } else {
          interp.interact(null, null);
        }
      } catch (Throwable t) {
        Py.printException(t);
      }
    }

    interp.cleanup();
  }

  /**
   * Run any finalizations on the current interpreter in preparation for a
   * SytemRestart.
   */
  public static void shutdownInterpreter() {
    // Stop all the active threads and signal the SystemRestart
    _thread.interruptAllThreads();
    Py.getSystemState()._systemRestart = true;
    // Close all sockets -- not all of their operations are stopped by
    // Thread.interrupt (in particular pre-nio sockets)
    try {
      imp.load("socket").__findattr__("_closeActiveSockets").__call__();
    } catch (PyException pye) {
      // continue
    }
  }

  /**
   * Return an array of trimmed strings by splitting the argument at each
   * occurrence of a
   * separator character. (Helper for configuration variable processing.) Segments
   * of zero length
   * after trimming emerge as <code>null</code>. If there are more than the
   * specified number of
   * segments the last element of the array contains all of the source string
   * after the
   * <code>(n-1)</code>th occurrence of <code>sep</code>.
   *
   * @param spec to split
   * @param sep  character on which to split
   * @param n    number of parts to split into
   * @return <code>n</code>-element array of strings (or <code>null</code>s)
   */
  private static String[] splitString(String spec, char sep, int n) {
    String[] list = new String[n];
    int p = 0, i = 0, L = spec.length();
    while (p < L) {
      int c = spec.indexOf(sep, p);
      if (c < 0 || i >= n - 1) {
        // No more seps, or no more space: i.th piece is the rest of spec.
        c = L;
      }
      String s = spec.substring(p, c).trim();
      list[i++] = (s.length() > 0) ? s : null;
      p = c + 1;
    }
    return list;
  }

  /**
   * If the key is not currently present and the passed value is not
   * <code>null</code>, sets the
   * <code>key</code> to the <code>value</code> in the given
   * <code>Properties</code> object. Thus,
   * it provides a default value for a subsequent <code>getProperty()</code>.
   *
   * @param registry to be (possibly) updated
   * @param key      at which to set value
   * @param value    to set (or <code>null</code> for no setting)
   * @return true iff a value was set
   */
  private static boolean addDefault(Properties registry, String key, String value) {
    // Set value at key if nothing else has set it
    if (value == null || registry.containsKey(key)) {
      return false;
    } else {
      registry.setProperty(key, value);
      return true;
    }
  }

  /**
   * Get the value of an environment variable, if we are allowed to and it exists;
   * otherwise
   * return <code>null</code>. We are allowed to access the environment variable
   * if the -E flag
   * was not given and the application has permission to read environment
   * variables. The -E flag
   * is reflected in {@link Options#ignore_environment}, and will be set
   * automatically if it turns
   * out we do not have permission.
   *
   * @param varname name to access in the environment
   * @return the value or <code>null</code>.
   */
  private static String getenv(String varname) {
    if (!Options.ignore_environment) {
      try {
        return System.getenv(varname);
      } catch (SecurityException e) {
        // We're not allowed to access them after all
        Options.ignore_environment = true;
      }
    }
    return null;
  }

}

class CommandLineOptions {

  public String filename;
  public boolean jar, interactive, notice;
  public boolean runCommand, runModule;
  public boolean fixInteractive;
  public boolean help, version;
  public String[] argv;
  public Properties properties;
  public String command;
  public List<String> warnoptions = Generic.list();
  public String encoding;
  public String division;
  public String moduleName;

  public CommandLineOptions() {
    filename = null;
    jar = fixInteractive = false;
    interactive = notice = true;
    runModule = false;
    properties = new Properties();
    help = version = false;
  }

  public void setProperty(String key, String value) {
    properties.put(key, value);
    try {
      System.setProperty(key, value);
    } catch (SecurityException e) {
      // continue
    }
  }

  private boolean argumentExpected(String arg) {
    System.err.println("Argument expected for the " + arg + " option");
    return false;
  }

  public boolean parse(String[] args) {
    int index = 0;

    while (index < args.length && args[index].startsWith("-")) {
      String arg = args[index];
      if (arg.equals("-h") || arg.equals("-?") || arg.equals("--help")) {
        help = true;
        return false;
      } else if (arg.equals("-V") || arg.equals("--version")) {
        version = true;
        return false;
      } else if (arg.equals("-")) {
        if (!fixInteractive) {
          interactive = false;
        }
        filename = "-";
      } else if (arg.equals("-i")) {
        fixInteractive = true;
        interactive = true;
      } else if (arg.equals("-jar")) {
        jar = true;
        if (!fixInteractive) {
          interactive = false;
        }
      } else if (arg.equals("-u")) {
        Options.unbuffered = true;
      } else if (arg.equals("-v")) {
        Options.verbose++;
      } else if (arg.equals("-vv")) {
        Options.verbose += 2;
      } else if (arg.equals("-vvv")) {
        Options.verbose += 3;
      } else if (arg.equals("-s")) {
        Options.no_user_site = true;
      } else if (arg.equals("-S")) {
        Options.importSite = false;
      } else if (arg.equals("-B")) {
        Options.dont_write_bytecode = true;
      } else if (arg.startsWith("-c")) {
        runCommand = true;
        if (arg.length() > 2) {
          command = arg.substring(2);
        } else if ((index + 1) < args.length) {
          command = args[++index];
        } else {
          return argumentExpected(arg);
        }
        if (!fixInteractive) {
          interactive = false;
        }
        index++;
        break;
      } else if (arg.startsWith("-W")) {
        if (arg.length() > 2) {
          warnoptions.add(arg.substring(2));
        } else if ((index + 1) < args.length) {
          warnoptions.add(args[++index]);
        } else {
          return argumentExpected(arg);
        }
      } else if (arg.equals("-E")) {
        // -E (ignore environment variables)
        Options.ignore_environment = true;
      } else if (arg.startsWith("-D")) {
        String key = null;
        String value = null;
        int equals = arg.indexOf("=");
        if (equals == -1) {
          String arg2 = args[++index];
          key = arg.substring(2, arg.length());
          value = arg2;
        } else {
          key = arg.substring(2, equals);
          value = arg.substring(equals + 1, arg.length());
        }
        setProperty(key, value);
      } else if (arg.startsWith("-Q")) {
        if (arg.length() > 2) {
          division = arg.substring(2);
        } else {
          division = args[++index];
        }
      } else if (arg.startsWith("-m")) {
        runModule = true;
        if (arg.length() > 2) {
          moduleName = arg.substring(2);
        } else if ((index + 1) < args.length) {
          moduleName = args[++index];
        } else {
          return argumentExpected(arg);
        }
        if (!fixInteractive) {
          interactive = false;
        }

        index++;
        int n = args.length - index + 1;
        argv = new String[n];
        argv[0] = moduleName;
        for (int i = 1; index < args.length; i++, index++) {
          argv[i] = args[index];
        }
        return true;
      } else if (arg.startsWith("-3")) {
        Options.py3k_warning = true;
      } else {
        String opt = args[index];
        if (opt.startsWith("--")) {
          opt = opt.substring(2);
        } else if (opt.startsWith("-")) {
          opt = opt.substring(1);
        }
        System.err.println("Unknown option: " + opt);
        return false;
      }
      index += 1;
    }
    notice = interactive;
    if (filename == null && index < args.length && command == null) {
      filename = args[index++];
      if (!fixInteractive) {
        interactive = false;
      }
      notice = false;
    }
    if (command != null) {
      notice = false;
    }

    int n = args.length - index + 1;
    argv = new String[n];
    if (filename != null) {
      argv[0] = filename;
    } else if (command != null) {
      argv[0] = "-c";
    } else {
      argv[0] = "";
    }

    for (int i = 1; i < n; i++, index++) {
      argv[i] = args[index];
    }

    return true;
  }
}
