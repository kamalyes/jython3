package org.kamalyes.jython3.antlr.adapter;

import java.util.ArrayList;
import java.util.List;

import org.kamalyes.jython3.antlr.ast.cmpopType;
import org.kamalyes.jython3.antlr.op.Eq;
import org.kamalyes.jython3.antlr.op.Gt;
import org.kamalyes.jython3.antlr.op.GtE;
import org.kamalyes.jython3.antlr.op.In;
import org.kamalyes.jython3.antlr.op.Is;
import org.kamalyes.jython3.antlr.op.IsNot;
import org.kamalyes.jython3.antlr.op.Lt;
import org.kamalyes.jython3.antlr.op.LtE;
import org.kamalyes.jython3.antlr.op.NotEq;
import org.kamalyes.jython3.antlr.op.NotIn;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;

public class CmpopAdapter implements AstAdapter {

  public Object py2ast(PyObject o) {
    if (o != Py.None) {
      switch ((o).asInt()) {
        case 1:
          return cmpopType.Eq;
        case 2:
          return cmpopType.NotEq;
        case 3:
          return cmpopType.Lt;
        case 4:
          return cmpopType.LtE;
        case 5:
          return cmpopType.Gt;
        case 6:
          return cmpopType.GtE;
        case 7:
          return cmpopType.Is;
        case 8:
          return cmpopType.IsNot;
        case 9:
          return cmpopType.In;
        case 10:
          return cmpopType.NotIn;
        default:
          return cmpopType.UNDEFINED;
      }
    }
    return cmpopType.UNDEFINED;
  }

  public PyObject ast2py(Object o) {
    if (o == null) {
      return Py.None;
    }
    switch ((cmpopType) o) {
      case Eq:
        return new Eq();
      case NotEq:
        return new NotEq();
      case Lt:
        return new Lt();
      case LtE:
        return new LtE();
      case Gt:
        return new Gt();
      case GtE:
        return new GtE();
      case Is:
        return new Is();
      case IsNot:
        return new IsNot();
      case In:
        return new In();
      case NotIn:
        return new NotIn();
      default:
        return Py.None;
    }
  }

  public List iter2ast(PyObject iter) {
    List<cmpopType> cmpops = new ArrayList<cmpopType>();
    if (iter != Py.None) {
      for (Object o : (Iterable) iter) {
        cmpops.add((cmpopType) py2ast((PyObject) o));
      }
    }
    return cmpops;
  }
}
