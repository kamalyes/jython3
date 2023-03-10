package org.kamalyes.jython3.core;

import org.kamalyes.jython3.antlr.base.mod;

public interface PythonCompiler {

  PythonCodeBundle compile(mod node, String name, String filename,
      boolean linenumbers, boolean printResults, CompilerFlags cflags)
      throws Exception;

}
