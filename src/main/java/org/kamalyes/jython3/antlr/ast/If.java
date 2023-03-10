// Autogenerated AST node
package org.kamalyes.jython3.antlr.ast;

import org.antlr.runtime.Token;
import org.kamalyes.jython3.antlr.PythonTree;
import org.kamalyes.jython3.antlr.adapter.AstAdapters;
import org.kamalyes.jython3.antlr.base.expr;
import org.kamalyes.jython3.antlr.base.stmt;
import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.AstList;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.core.PyStringMap;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedSet;
import org.kamalyes.jython3.expose.ExposedType;

import java.util.ArrayList;

@ExposedType(name = "_ast.If", base = stmt.class)
public class If extends stmt {
  public static final PyType TYPE = PyType.fromClass(If.class);
  private expr test;

  public expr getInternalTest() {
    return test;
  }

  @ExposedGet(name = "test")
  public PyObject getTest() {
    return test;
  }

  @ExposedSet(name = "test")
  public void setTest(PyObject test) {
    this.test = AstAdapters.py2expr(test);
  }

  private java.util.List<stmt> body;

  public java.util.List<stmt> getInternalBody() {
    return body;
  }

  @ExposedGet(name = "body")
  public PyObject getBody() {
    return new AstList(body, AstAdapters.stmtAdapter);
  }

  @ExposedSet(name = "body")
  public void setBody(PyObject body) {
    this.body = AstAdapters.py2stmtList(body);
  }

  private java.util.List<stmt> orelse;

  public java.util.List<stmt> getInternalOrelse() {
    return orelse;
  }

  @ExposedGet(name = "orelse")
  public PyObject getOrelse() {
    return new AstList(orelse, AstAdapters.stmtAdapter);
  }

  @ExposedSet(name = "orelse")
  public void setOrelse(PyObject orelse) {
    this.orelse = AstAdapters.py2stmtList(orelse);
  }

  private final static PyUnicode[] fields = new PyUnicode[] { new PyUnicode("test"), new PyUnicode("body"),
      new PyUnicode("orelse") };

  @ExposedGet(name = "_fields")
  public PyUnicode[] get_fields() {
    return fields;
  }

  private final static PyUnicode[] attributes = new PyUnicode[] { new PyUnicode("lineno"),
      new PyUnicode("col_offset") };

  @ExposedGet(name = "_attributes")
  public PyUnicode[] get_attributes() {
    return attributes;
  }

  public If(PyType subType) {
    super(subType);
  }

  public If() {
    this(TYPE);
  }

  @ExposedNew
  @ExposedMethod
  public void If___init__(PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("If", args, keywords,
        new String[] { "test", "body", "orelse", "lineno", "col_offset" }, 3, true);
    setTest(ap.getPyObject(0, Py.None));
    setBody(ap.getPyObject(1, Py.None));
    setOrelse(ap.getPyObject(2, Py.None));
    int lin = ap.getInt(3, -1);
    if (lin != -1) {
      setLineno(lin);
    }

    int col = ap.getInt(4, -1);
    if (col != -1) {
      setLineno(col);
    }

  }

  public If(PyObject test, PyObject body, PyObject orelse) {
    setTest(test);
    setBody(body);
    setOrelse(orelse);
  }

  public If(Token token, expr test, java.util.List<stmt> body, java.util.List<stmt> orelse) {
    super(token);
    this.test = test;
    addChild(test);
    this.body = body;
    if (body == null) {
      this.body = new ArrayList<stmt>();
    }
    for (PythonTree t : this.body) {
      addChild(t);
    }
    this.orelse = orelse;
    if (orelse == null) {
      this.orelse = new ArrayList<stmt>();
    }
    for (PythonTree t : this.orelse) {
      addChild(t);
    }
  }

  public If(Integer ttype, Token token, expr test, java.util.List<stmt> body,
      java.util.List<stmt> orelse) {
    super(ttype, token);
    this.test = test;
    addChild(test);
    this.body = body;
    if (body == null) {
      this.body = new ArrayList<stmt>();
    }
    for (PythonTree t : this.body) {
      addChild(t);
    }
    this.orelse = orelse;
    if (orelse == null) {
      this.orelse = new ArrayList<stmt>();
    }
    for (PythonTree t : this.orelse) {
      addChild(t);
    }
  }

  public If(PythonTree tree, expr test, java.util.List<stmt> body, java.util.List<stmt> orelse) {
    super(tree);
    this.test = test;
    addChild(test);
    this.body = body;
    if (body == null) {
      this.body = new ArrayList<stmt>();
    }
    for (PythonTree t : this.body) {
      addChild(t);
    }
    this.orelse = orelse;
    if (orelse == null) {
      this.orelse = new ArrayList<stmt>();
    }
    for (PythonTree t : this.orelse) {
      addChild(t);
    }
  }

  @ExposedGet(name = "repr")
  public String toString() {
    return "If";
  }

  public String toStringTree() {
    StringBuffer sb = new StringBuffer("If(");
    sb.append("test=");
    sb.append(dumpThis(test));
    sb.append(",");
    sb.append("body=");
    sb.append(dumpThis(body));
    sb.append(",");
    sb.append("orelse=");
    sb.append(dumpThis(orelse));
    sb.append(",");
    sb.append(")");
    return sb.toString();
  }

  public <R> R accept(VisitorIF<R> visitor) throws Exception {
    return visitor.visitIf(this);
  }

  public void traverse(VisitorIF<?> visitor) throws Exception {
    if (test != null)
      test.accept(visitor);
    if (body != null) {
      for (PythonTree t : body) {
        if (t != null)
          t.accept(visitor);
      }
    }
    if (orelse != null) {
      for (PythonTree t : orelse) {
        if (t != null)
          t.accept(visitor);
      }
    }
  }

  public PyObject __dict__;

  @Override
  public PyObject fastGetDict() {
    ensureDict();
    return __dict__;
  }

  @ExposedGet(name = "__dict__")
  public PyObject getDict() {
    return fastGetDict();
  }

  private void ensureDict() {
    if (__dict__ == null) {
      __dict__ = new PyStringMap();
    }
  }

  private int lineno = -1;

  @ExposedGet(name = "lineno")
  public int getLineno() {
    if (lineno != -1) {
      return lineno;
    }
    return getLine();
  }

  @ExposedSet(name = "lineno")
  public void setLineno(int num) {
    lineno = num;
  }

  private int col_offset = -1;

  @ExposedGet(name = "col_offset")
  public int getCol_offset() {
    if (col_offset != -1) {
      return col_offset;
    }
    return getCharPositionInLine();
  }

  @ExposedSet(name = "col_offset")
  public void setCol_offset(int num) {
    col_offset = num;
  }

}
