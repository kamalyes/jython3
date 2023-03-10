// Autogenerated AST node
package org.kamalyes.jython3.antlr.ast;

import org.antlr.runtime.Token;
import org.kamalyes.jython3.antlr.PythonTree;
import org.kamalyes.jython3.antlr.adapter.AstAdapters;
import org.kamalyes.jython3.antlr.base.expr;
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

@ExposedType(name = "_ast.Compare", base = expr.class)
public class Compare extends expr {
  public static final PyType TYPE = PyType.fromClass(Compare.class);
  private expr left;

  public expr getInternalLeft() {
    return left;
  }

  @ExposedGet(name = "left")
  public PyObject getLeft() {
    return left;
  }

  @ExposedSet(name = "left")
  public void setLeft(PyObject left) {
    this.left = AstAdapters.py2expr(left);
  }

  private java.util.List<cmpopType> ops;

  public java.util.List<cmpopType> getInternalOps() {
    return ops;
  }

  @ExposedGet(name = "ops")
  public PyObject getOps() {
    return new AstList(ops, AstAdapters.cmpopAdapter);
  }

  @ExposedSet(name = "ops")
  public void setOps(PyObject ops) {
    this.ops = AstAdapters.py2cmpopList(ops);
  }

  private java.util.List<expr> comparators;

  public java.util.List<expr> getInternalComparators() {
    return comparators;
  }

  @ExposedGet(name = "comparators")
  public PyObject getComparators() {
    return new AstList(comparators, AstAdapters.exprAdapter);
  }

  @ExposedSet(name = "comparators")
  public void setComparators(PyObject comparators) {
    this.comparators = AstAdapters.py2exprList(comparators);
  }

  private final static PyUnicode[] fields = new PyUnicode[] { new PyUnicode("left"), new PyUnicode("ops"),
      new PyUnicode("comparators") };

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

  public Compare(PyType subType) {
    super(subType);
  }

  public Compare() {
    this(TYPE);
  }

  @ExposedNew
  @ExposedMethod
  public void Compare___init__(PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("Compare", args, keywords,
        new String[] { "left", "ops", "comparators", "lineno", "col_offset" }, 3, true);
    setLeft(ap.getPyObject(0, Py.None));
    setOps(ap.getPyObject(1, Py.None));
    setComparators(ap.getPyObject(2, Py.None));
    int lin = ap.getInt(3, -1);
    if (lin != -1) {
      setLineno(lin);
    }

    int col = ap.getInt(4, -1);
    if (col != -1) {
      setLineno(col);
    }

  }

  public Compare(PyObject left, PyObject ops, PyObject comparators) {
    setLeft(left);
    setOps(ops);
    setComparators(comparators);
  }

  public Compare(Token token, expr left, java.util.List<cmpopType> ops, java.util.List<expr> comparators) {
    super(token);
    this.left = left;
    addChild(left);
    this.ops = ops;
    this.comparators = comparators;
    if (comparators == null) {
      this.comparators = new ArrayList<expr>();
    }
    for (PythonTree t : this.comparators) {
      addChild(t);
    }
  }

  public Compare(Integer ttype, Token token, expr left, java.util.List<cmpopType> ops,
      java.util.List<expr> comparators) {
    super(ttype, token);
    this.left = left;
    addChild(left);
    this.ops = ops;
    this.comparators = comparators;
    if (comparators == null) {
      this.comparators = new ArrayList<expr>();
    }
    for (PythonTree t : this.comparators) {
      addChild(t);
    }
  }

  public Compare(PythonTree tree, expr left, java.util.List<cmpopType> ops, java.util.List<expr> comparators) {
    super(tree);
    this.left = left;
    addChild(left);
    this.ops = ops;
    this.comparators = comparators;
    if (comparators == null) {
      this.comparators = new ArrayList<expr>();
    }
    for (PythonTree t : this.comparators) {
      addChild(t);
    }
  }

  @ExposedGet(name = "repr")
  public String toString() {
    return "Compare";
  }

  public String toStringTree() {
    StringBuffer sb = new StringBuffer("Compare(");
    sb.append("left=");
    sb.append(dumpThis(left));
    sb.append(",");
    sb.append("ops=");
    sb.append(dumpThis(ops));
    sb.append(",");
    sb.append("comparators=");
    sb.append(dumpThis(comparators));
    sb.append(",");
    sb.append(")");
    return sb.toString();
  }

  public <R> R accept(VisitorIF<R> visitor) throws Exception {
    return visitor.visitCompare(this);
  }

  public void traverse(VisitorIF<?> visitor) throws Exception {
    if (left != null)
      left.accept(visitor);
    if (comparators != null) {
      for (PythonTree t : comparators) {
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
