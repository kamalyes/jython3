package org.kamalyes.jython3.antlr.ast;

import org.kamalyes.jython3.antlr.PythonTree;
import org.kamalyes.jython3.antlr.base.stmt;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import java.io.DataOutputStream;
import java.io.IOException;

public class ErrorStmt extends stmt {

  public ErrorStmt(PythonTree tree) {
    super(tree);
  }

  public String toString() {
    return "ErrorStmt";
  }

  public String toStringTree() {
    return "ErrorStmt";
  }

  public int getLineno() {
    return getLine();
  }

  public int getCol_offset() {
    return getCharPositionInLine();
  }

  public <R> R accept(VisitorIF<R> visitor) {
    return null;
  }

  public void traverse(VisitorIF visitor) throws Exception {
    // no op.
  }

}
