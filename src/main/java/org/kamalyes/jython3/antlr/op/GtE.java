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

@ExposedType(name = "_ast.GtE", base = cmpop.class)
public class GtE extends PythonTree {
  public static final PyType TYPE = PyType.fromClass(GtE.class);

  public GtE() {
  }

  public GtE(PyType subType) {
    super(subType);
  }

  @ExposedNew
  @ExposedMethod
  public void GtE___init__(PyObject[] args, String[] keywords) {
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
    return GtE___int__();
  }

  final PyObject GtE___int__() {
    return Py.newInteger(6);
  }

}
