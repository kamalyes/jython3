package org.kamalyes.jython3.modules.subprocess;

import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.BuiltinDocs;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyDictionary;
import org.kamalyes.jython3.core.PyException;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyTuple;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.expose.ExposedConst;
import org.kamalyes.jython3.expose.ExposedFunction;
import org.kamalyes.jython3.expose.ExposedModule;
import org.kamalyes.jython3.expose.ModuleInit;

@ExposedModule(name = "subprocess")
public class SubprocessModule {
  /**
   * Python's PIPE is ProcessBuilder.Redirect.PIPE
   */
  @ExposedConst
  public static final int PIPE = -1;

  /**
   * STDOUT means ProcessBuilder.redirectErrorStream(true)
   */
  @ExposedConst
  public static final int STDOUT = -2;

  /**
   * No counterpart in java yet, use shell with pipe redirection
   */
  @ExposedConst
  public static final int DEVNULL = -3;

  private static PyObject SubprocessError;
  private static PyObject CalledProcessError;

  @ModuleInit
  public static final void classDictInit(PyObject dict) {
    PyObject exc = Py.getSystemState().getBuiltins();
    PyObject superclass = exc.__finditem__("Exception");
    PyDictionary classDict = new PyDictionary();
    classDict.__setitem__("__doc__", new PyUnicode("Common base class for all non-exit exceptions."));
    SubprocessError = Py.makeClass("SubprocessError", superclass, classDict);
    classDict = new PyDictionary();
    classDict.__setitem__("__doc__", new PyUnicode("This exception is raised when check_returncode()" +
        " is called on a process that exit code is non-zero"));
    CalledProcessError = Py.makeClass("CalledProcessError", SubprocessError, classDict);
    dict.__setitem__("SubprocessError", SubprocessError);
    dict.__setitem__("CalledProcessError", CalledProcessError);
    dict.__setitem__("Popen", PyPopen.TYPE);
    dict.__setitem__("CompletedProcess", PyCompletedProcess.TYPE);
  }

  @ExposedFunction
  public static final PyObject run(PyObject[] arguments, String[] kwds) {
    ArgParser ap = new ArgParser("run", arguments, kwds, 1, "args", "*", "stdin", "input",
        "stdout", "stderr", "shell", "timeout", "check");
    PyObject args = ap.getPyObject(0);
    int stdin = ap.getInt(2, 0);
    PyObject input = ap.getPyObject(3, Py.None);
    int stdout = ap.getInt(4, 0);
    int stderr = ap.getInt(5, 0);
    boolean shell = ap.getPyObject(6, Py.False).__bool__();
    PyObject timeout = ap.getPyObject(7, Py.None);
    boolean check = ap.getPyObject(8, Py.False).__bool__();
    PyPopen popen = new PyPopen(args, stdin, stdout, stderr, Py.None, null);
    PyTuple res;
    if (input != Py.None) {
      res = (PyTuple) popen.Popen_communicate(new PyObject[] { input }, Py.NoKeywords);
    } else {
      res = (PyTuple) popen.Popen_communicate(Py.EmptyObjects, Py.NoKeywords);
    }
    return new PyCompletedProcess(args, popen.process());
  }

  @ExposedFunction
  public static final PyObject call(PyObject[] arguments, String[] keywords) {
    ArgParser ap = new ArgParser("call", arguments, keywords, "args", "*", "stdin", "stdout", "stderr", "shell",
        "timeout");
    PyObject args = ap.getPyObject(0);
    int stdin = ap.getInt(2, 0);
    int stdout = ap.getInt(3, 0);
    int stderr = ap.getInt(4, 0);
    boolean shell = ap.getPyObject(5, Py.False).__bool__();
    int timeout = ap.getInt(6, -1);
    PyPopen popen = new PyPopen(args, stdin, stdout, stderr, Py.None, null);
    return popen.Popen_wait(Py.EmptyObjects, Py.NoKeywords);
  }

  @ExposedFunction
  public static final PyObject check_call(PyObject[] arguments, String[] keywords) {
    ArgParser ap = new ArgParser("call", arguments, keywords, "args", "*", "stdin", "stdout", "stderr", "shell",
        "timeout");
    PyObject args = ap.getPyObject(0);
    int stdin = ap.getInt(2, 0);
    int stdout = ap.getInt(3, 0);
    int stderr = ap.getInt(4, 0);
    boolean shell = ap.getPyObject(5, Py.False).__bool__();
    int timeout = ap.getInt(6, -1);
    PyPopen popen = new PyPopen(args, stdin, stdout, stderr, Py.None, null);
    PyObject res = popen.Popen_wait(Py.EmptyObjects, Py.NoKeywords);
    if (res.equals(Py.Zero)) {
      return res;
    }
    popen.check();
    // cannot reach
    return Py.None;
  }

  @ExposedFunction
  public static final PyObject check_output(PyObject[] arguments, String[] keywords) {
    ArgParser ap = new ArgParser("call", arguments, keywords, "args", "*", "stdin", "stdout", "stderr", "shell",
        "timeout");
    PyObject args = ap.getPyObject(0);
    int stdin = ap.getInt(2, 0);
    int stdout = ap.getInt(3, 0);
    int stderr = ap.getInt(4, 0);
    boolean shell = ap.getPyObject(5, Py.False).__bool__();
    int timeout = ap.getInt(6, -1);
    PyPopen popen = new PyPopen(args, stdin, stdout, stderr, Py.None, null);
    PyTuple res = (PyTuple) popen.Popen_communicate(Py.EmptyObjects, Py.NoKeywords);
    popen.check();
    return res.pyget(0);
  }

  public static PyException CalledProcessError() {
    return new PyException(CalledProcessError);
  }
}
