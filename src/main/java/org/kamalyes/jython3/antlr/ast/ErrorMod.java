package org.kamalyes.jython3.antlr.ast;

import org.kamalyes.jython3.antlr.PythonTree;
import org.kamalyes.jython3.antlr.base.mod;

public class ErrorMod extends mod {

  public ErrorMod(PythonTree tree) {
    super(tree);
  }

  public String toString() {
    return "ErrorMod";
  }

  public String toStringTree() {
    return "ErrorMod";
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
