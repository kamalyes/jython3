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

@ExposedType(name = "_ast.Constant", base = expr.class)
public class Constant extends expr {
  public static final PyType TYPE = PyType.fromClass(Constant.class);
  private String value;

  public String getInternalValue() {
    return value;
  }

  @ExposedGet(name = "value")
  public PyObject getValue() {
    return AstAdapters.constant2py(value);
  }

  @ExposedSet(name = "value")
  public void setValue(PyObject value) {
    this.value = AstAdapters.py2constant(value);
  }

  private final static PyUnicode[] fields = new PyUnicode[] { new PyUnicode("value") };

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

  public Constant(PyType subType) {
    super(subType);
  }

  public Constant() {
    this(TYPE);
  }

  @ExposedNew
  @ExposedMethod
  public void Constant___init__(PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("Constant", args, keywords, new String[] { "value", "lineno", "col_offset" }, 1, true);
    setValue(ap.getPyObject(0, Py.None));
    int lin = ap.getInt(1, -1);
    if (lin != -1) {
      setLineno(lin);
    }

    int col = ap.getInt(2, -1);
    if (col != -1) {
      setLineno(col);
    }

  }

  public Constant(PyObject value) {
    setValue(value);
  }

  public Constant(Token token, String value) {
    super(token);
    this.value = value;
  }

  public Constant(Integer ttype, Token token, String value) {
    super(ttype, token);
    this.value = value;
  }

  public Constant(PythonTree tree, String value) {
    super(tree);
    this.value = value;
  }

  @ExposedGet(name = "repr")
  public String toString() {
    return "Constant";
  }

  public String toStringTree() {
    StringBuffer sb = new StringBuffer("Constant(");
    sb.append("value=");
    sb.append(dumpThis(value));
    sb.append(",");
    sb.append(")");
    return sb.toString();
  }

  public <R> R accept(VisitorIF<R> visitor) throws Exception {
    return visitor.visitConstant(this);
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
