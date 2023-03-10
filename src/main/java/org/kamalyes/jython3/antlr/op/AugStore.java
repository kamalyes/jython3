// Autogenerated AST node
package org.kamalyes.jython3.antlr.op;

import org.kamalyes.jython3.antlr.base.expr_context;
import org.kamalyes.jython3.antlr.PythonTree;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "_ast.AugStore", base = expr_context.class)
public class AugStore extends PythonTree {
  public static final PyType TYPE = PyType.fromClass(AugStore.class);

  public AugStore() {
  }

  public AugStore(PyType subType) {
    super(subType);
  }

  @ExposedNew
  @ExposedMethod
  public void AugStore___init__(PyObject[] args, String[] keywords) {
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
    return AugStore___int__();
  }

  final PyObject AugStore___int__() {
    return Py.newInteger(5);
  }

}
