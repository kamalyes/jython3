package org.kamalyes.jython3.antlr.adapter;

import org.kamalyes.jython3.antlr.ast.withitem;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;

import java.util.ArrayList;
import java.util.List;

public class WithitemAdapter implements AstAdapter {
  @Override
  public PyObject ast2py(Object o) {

    if (o == null) {
      return Py.None;
    }
    return (PyObject) o;

  }

  @Override
  public Object py2ast(PyObject o) {
    if (o instanceof withitem) {
      return o;
    }
    return null;
  }

  @Override
  public List iter2ast(PyObject iter) {
    List<withitem> items = new ArrayList<>();
    if (iter != Py.None) {
      for (Object o : (Iterable) iter) {
        items.add((withitem) py2ast((PyObject) o));
      }
    }
    return items;
  }
}
