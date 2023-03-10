// Autogenerated AST node
package org.kamalyes.jython3.antlr.ast;

import org.antlr.runtime.Token;
import org.kamalyes.jython3.antlr.PythonTree;
import org.kamalyes.jython3.antlr.adapter.AstAdapters;
import org.kamalyes.jython3.antlr.base.expr;
import org.kamalyes.jython3.core.ArgParser;
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

@ExposedType(name = "_ast.Bytes", base = expr.class)
public class Bytes extends expr {
  public static final PyType TYPE = PyType.fromClass(Bytes.class);
  private String s;

  public String getInternalS() {
    return s;
  }

  @ExposedGet(name = "s")
  public PyObject getS() {
    return AstAdapters.bytes2py(s);
  }

  @ExposedSet(name = "s")
  public void setS(PyObject s) {
    this.s = AstAdapters.py2bytes(s);
  }

  private final static PyUnicode[] fields = new PyUnicode[] { new PyUnicode("s") };

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

  public Bytes(PyType subType) {
    super(subType);
  }

  public Bytes() {
    this(TYPE);
  }

  @ExposedNew
  @ExposedMethod
  public void Bytes___init__(PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("Bytes", args, keywords, new String[] { "s", "lineno", "col_offset" }, 1, true);
    setS(ap.getPyObject(0, Py.None));
    int lin = ap.getInt(1, -1);
    if (lin != -1) {
      setLineno(lin);
    }

    int col = ap.getInt(2, -1);
    if (col != -1) {
      setLineno(col);
    }

  }

  public Bytes(PyObject s) {
    setS(s);
  }

  public Bytes(Token token, String s) {
    super(token);
    this.s = s;
  }

  public Bytes(Integer ttype, Token token, String s) {
    super(ttype, token);
    this.s = s;
  }

  public Bytes(PythonTree tree, String s) {
    super(tree);
    this.s = s;
  }

  @ExposedGet(name = "repr")
  public String toString() {
    return "Bytes";
  }

  public String toStringTree() {
    StringBuffer sb = new StringBuffer("Bytes(");
    sb.append("s=");
    sb.append(dumpThis(s));
    sb.append(",");
    sb.append(")");
    return sb.toString();
  }

  public <R> R accept(VisitorIF<R> visitor) throws Exception {
    return visitor.visitBytes(this);
  }

  public void traverse(VisitorIF<?> visitor) throws Exception {
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
