package org.kamalyes.jython3.antlr.adapter;

import java.util.ArrayList;
import java.util.List;

import org.kamalyes.jython3.antlr.base.stmt;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;

public class StmtAdapter implements AstAdapter {

  public Object py2ast(PyObject o) {
    if (o instanceof stmt) {
      return o;
    }
    return null;
  }

  public PyObject ast2py(Object o) {
    if (o == null) {
      return Py.None;
    }
    return (PyObject) o;
  }

  public List iter2ast(PyObject iter) {
    List<stmt> stmts = new ArrayList<stmt>();
    if (iter != Py.None) {
      for (Object o : (Iterable) iter) {
        stmts.add((stmt) py2ast((PyObject) o));
      }
    }
    return stmts;
  }
}
