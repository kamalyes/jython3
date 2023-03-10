// Autogenerated AST node
package org.kamalyes.jython3.antlr.ast;

import org.antlr.runtime.Token;
import org.kamalyes.jython3.antlr.PythonTree;
import org.kamalyes.jython3.antlr.adapter.AstAdapters;
import org.kamalyes.jython3.antlr.base.expr;
import org.kamalyes.jython3.antlr.base.stmt;
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

@ExposedType(name = "_ast.AugAssign", base = stmt.class)
public class AugAssign extends stmt {
  public static final PyType TYPE = PyType.fromClass(AugAssign.class);
  private expr target;

  public expr getInternalTarget() {
    return target;
  }

  @ExposedGet(name = "target")
  public PyObject getTarget() {
    return target;
  }

  @ExposedSet(name = "target")
  public void setTarget(PyObject target) {
    this.target = AstAdapters.py2expr(target);
  }

  private operatorType op;

  public operatorType getInternalOp() {
    return op;
  }

  @ExposedGet(name = "op")
  public PyObject getOp() {
    return AstAdapters.operator2py(op);
  }

  @ExposedSet(name = "op")
  public void setOp(PyObject op) {
    this.op = AstAdapters.py2operator(op);
  }

  private expr value;

  public expr getInternalValue() {
    return value;
  }

  @ExposedGet(name = "value")
  public PyObject getValue() {
    return value;
  }

  @ExposedSet(name = "value")
  public void setValue(PyObject value) {
    this.value = AstAdapters.py2expr(value);
  }

  private final static PyUnicode[] fields = new PyUnicode[] { new PyUnicode("target"), new PyUnicode("op"),
      new PyUnicode("value") };

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

  public AugAssign(PyType subType) {
    super(subType);
  }

  public AugAssign() {
    this(TYPE);
  }

  @ExposedNew
  @ExposedMethod
  public void AugAssign___init__(PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("AugAssign", args, keywords,
        new String[] { "target", "op", "value", "lineno", "col_offset" }, 3, true);
    setTarget(ap.getPyObject(0, Py.None));
    setOp(ap.getPyObject(1, Py.None));
    setValue(ap.getPyObject(2, Py.None));
    int lin = ap.getInt(3, -1);
    if (lin != -1) {
      setLineno(lin);
    }

    int col = ap.getInt(4, -1);
    if (col != -1) {
      setLineno(col);
    }

  }

  public AugAssign(PyObject target, PyObject op, PyObject value) {
    setTarget(target);
    setOp(op);
    setValue(value);
  }

  public AugAssign(Token token, expr target, operatorType op, expr value) {
    super(token);
    this.target = target;
    addChild(target);
    this.op = op;
    this.value = value;
    addChild(value);
  }

  public AugAssign(Integer ttype, Token token, expr target, operatorType op, expr value) {
    super(ttype, token);
    this.target = target;
    addChild(target);
    this.op = op;
    this.value = value;
    addChild(value);
  }

  public AugAssign(PythonTree tree, expr target, operatorType op, expr value) {
    super(tree);
    this.target = target;
    addChild(target);
    this.op = op;
    this.value = value;
    addChild(value);
  }

  @ExposedGet(name = "repr")
  public String toString() {
    return "AugAssign";
  }

  public String toStringTree() {
    StringBuffer sb = new StringBuffer("AugAssign(");
    sb.append("target=");
    sb.append(dumpThis(target));
    sb.append(",");
    sb.append("op=");
    sb.append(dumpThis(op));
    sb.append(",");
    sb.append("value=");
    sb.append(dumpThis(value));
    sb.append(",");
    sb.append(")");
    return sb.toString();
  }

  public <R> R accept(VisitorIF<R> visitor) throws Exception {
    return visitor.visitAugAssign(this);
  }

  public void traverse(VisitorIF<?> visitor) throws Exception {
    if (target != null)
      target.accept(visitor);
    if (value != null)
      value.accept(visitor);
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
