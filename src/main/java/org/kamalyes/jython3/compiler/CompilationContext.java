
package org.kamalyes.jython3.compiler;

import org.kamalyes.jython3.antlr.PythonTree;

public interface CompilationContext {

  public Future getFutures();

  public void error(String msg, boolean err, PythonTree node)
      throws Exception;

  public String getFilename();

  public ScopeInfo getScopeInfo(PythonTree node);
}
