package org.kamalyes.jython3.antlr.ast;

import org.kamalyes.jython3.antlr.PythonTree;
import org.kamalyes.jython3.antlr.base.slice;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import java.io.DataOutputStream;
import java.io.IOException;

public class ErrorSlice extends slice {

  public ErrorSlice(PythonTree tree) {
    super(tree);
  }

  public String toString() {
    return "ErrorSlice";
  }

  public String toStringTree() {
    return "ErrorSlice";
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
