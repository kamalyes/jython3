package org.kamalyes.jython3.antlr.adapter;

import java.util.ArrayList;
import java.util.List;

import org.kamalyes.jython3.antlr.ast.Num;
import org.kamalyes.jython3.antlr.ast.Str;
import org.kamalyes.jython3.antlr.base.expr;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyBytes;
import org.kamalyes.jython3.core.PyComplex;
import org.kamalyes.jython3.core.PyFloat;
import org.kamalyes.jython3.core.PyInteger;
import org.kamalyes.jython3.core.PyLong;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyUnicode;

public class ExprAdapter implements AstAdapter {

  public Object py2ast(PyObject o) {
    if (o instanceof expr) {
      return o;
    }
    if (o instanceof PyInteger || o instanceof PyLong || o instanceof PyFloat || o instanceof PyComplex) {
      return new Num(o);
    }
    if (o instanceof PyBytes || o instanceof PyUnicode) {
      return new Str(o);
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
    List<expr> exprs = new ArrayList<expr>();
    if (iter != Py.None) {
      for (Object o : (Iterable) iter) {
        exprs.add((expr) py2ast((PyObject) o));
      }
    }
    return exprs;
  }
}
