// Autogenerated AST node
package org.kamalyes.jython3.antlr.op;

import org.kamalyes.jython3.antlr.base.cmpop;
import org.kamalyes.jython3.antlr.PythonTree;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "_ast.NotEq", base = cmpop.class)
public class NotEq extends PythonTree {
  public static final PyType TYPE = PyType.fromClass(NotEq.class);

  public NotEq() {
  }

  public NotEq(PyType subType) {
    super(subType);
  }

  @ExposedNew
  @ExposedMethod
  public void NotEq___init__(PyObject[] args, String[] keywords) {
  }

  private final static PyUnicode[] fields = new PyUnicode[0];

  @ExposedGet(name = "_fields")
  public PyUnicode[] get_fields() {
    return fields;
  }

  private final static PyUnicode[] attributes = new PyUnicode[0];

  @ExposedGet(name = "_attributes")
  public PyUnicode[] get_attributes() {
    return attributes;
  }

  @ExposedMethod
  public PyObject __int__() {
    return NotEq___int__();
  }

  final PyObject NotEq___int__() {
    return Py.newInteger(2);
  }

}
