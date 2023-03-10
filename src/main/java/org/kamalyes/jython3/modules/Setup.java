// Copyright (c) Corporation for National Research Initiatives
package org.kamalyes.jython3.modules;

import org.kamalyes.jython3.modules.posix.PosixModule;

// This is sort of analogous to CPython's Modules/Setup file.  Use this to
// specify additional builtin modules.

public class Setup {
  // Each element of this array is a string naming a builtin module to
  // add to the system. The string has the following allowable forms:
  //
  // name
  // The module name is `name' and the class name is
  // org.kamalyes.jython3.modules.name
  //
  // name:class
  // The module name is `name' and the class name is `class' where
  // class must be a fully qualified Java class name
  //
  // name:null
  // The module `name' is removed from the list of builtin modules
  //
  // That isn't very useful here, but you can add additional builtin
  // modules by editing the Jython registry file. See the property
  // python.modules.builtin for details.

  public static String[] builtinModules = {
      "_bytecodetools",
      // "_codecs",
      "_hashlib",
      "_io:org.kamalyes.jython3.modules._io._io",
      "_json:org.kamalyes.jython3.modules._json._json",
      "_jythonlib:org.kamalyes.jython3.modules._jythonlib._jythonlib",
      "_random:org.kamalyes.jython3.modules.random.RandomModule",
      // "_sre",
      // "_string",
      "_systemrestart",
      // "_types",
      // "_weakref:org.kamalyes.jython3.modules._weakref.WeakrefModule",
      "_pickle",
      "cmath",
      "errno",
      "fcntl",
      "gc",
      "jarray",
      "jffi:org.kamalyes.jython3.modules.jffi.jffi",
      "marshal",
      "math",
      "operator",
      "struct",
      "synchronize",
      // "_thread:org.kamalyes.jython3.modules.thread.thread",
      // PosixModule.getOSName() + ":org.kamalyes.jython3.modules.posix.PosixModule"
  };

  public static String[] newbuiltinModules = {
      "_ast:org.kamalyes.jython3.antlr.ast.AstModule",
      "_bz2:org.kamalyes.jython3.modules.bz2.bz2",
      "_codecs",
      "_codecs_cn:org.kamalyes.jython3.modules.cjkcodecs._codecs_cn",
      "_codecs_tw:org.kamalyes.jython3.modules.cjkcodecs._codecs_tw",
      "_codecs_hk:org.kamalyes.jython3.modules.cjkcodecs._codecs_hk",
      "_codecs_kr:org.kamalyes.jython3.modules.cjkcodecs._codecs_kr",
      "_codecs_jp:org.kamalyes.jython3.modules.cjkcodecs._codecs_jp",
      "_collections:org.kamalyes.jython3.modules._collections.Collections",
      "_csv:org.kamalyes.jython3.modules._csv._csv",
      "_datetime:org.kamalyes.jython3.modules._datetime.DatetimeModule",
      "_functools:org.kamalyes.jython3.modules._functools._functools",
      "_imp:org.kamalyes.jython3.modules._imp",
      "_multibytecodec:org.kamalyes.jython3.modules.cjkcodecs._multibytecodec",
      "_multiprocessing:org.kamalyes.jython3.modules._multiprocessing._multiprocessing",
      "_posixsubprocess",
      "_sre",
      "_string",
      "_types",
      "_thread:org.kamalyes.jython3.modules.thread._thread",
      "_warnings",
      "_weakref:org.kamalyes.jython3.modules._weakref.WeakrefModule",
      "array:org.kamalyes.jython3.modules.ArrayModule",
      "binascii",
      "faulthandler:org.kamalyes.jython3.modules.FaultHandler",
      "itertools:org.kamalyes.jython3.modules.itertools.itertools",
      "posix:org.kamalyes.jython3.modules.posix.PosixModule",
      "subprocess:org.kamalyes.jython3.modules.subprocess.SubprocessModule",
      "sys:org.kamalyes.jython3.modules.sys.SysModule",
      "time:org.kamalyes.jython3.modules.time.TimeModule",
      "unicodedata:org.kamalyes.jython3.modules.unicodedata.unicodedata",
      "zipimport:org.kamalyes.jython3.modules.zipimport.ZipImportModule",
      "zlib:org.kamalyes.jython3.modules.zlib.ZlibModule",
  };
}
