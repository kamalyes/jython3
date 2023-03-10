// Hand copied from stmt.
// TODO: autogenerate this.
package org.kamalyes.jython3.antlr.base;

import org.antlr.runtime.Token;
import org.kamalyes.jython3.antlr.AST;
import org.kamalyes.jython3.antlr.PythonTree;
import org.kamalyes.jython3.core.PyBytes;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "_ast.cmpop", base = AST.class)
public abstract class cmpop extends PythonTree {

  public static final PyType TYPE = PyType.fromClass(cmpop.class);
  private final static PyBytes[] fields = new PyBytes[0];

  @ExposedGet(name = "_fields")
  public PyBytes[] get_fields() {
    return fields;
  }

  private final static PyBytes[] attributes = new PyBytes[] { new PyBytes("lineno"), new PyBytes("col_offset") };

  @ExposedGet(name = "_attributes")
  public PyBytes[] get_attributes() {
    return attributes;
  }

  public cmpop() {
  }

  public cmpop(PyType subType) {
  }

  public cmpop(int ttype, Token token) {
    super(ttype, token);
  }

  public cmpop(Token token) {
    super(token);
  }

  public cmpop(PythonTree node) {
    super(node);
  }

}
