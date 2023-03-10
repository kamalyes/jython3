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

@ExposedType(name = "_ast.ListComp", base = expr.class)
public class ListComp extends expr {
  public static final PyType TYPE = PyType.fromClass(ListComp.class);
  private expr elt;

  public expr getInternalElt() {
    return elt;
  }

  @ExposedGet(name = "elt")
  public PyObject getElt() {
    return elt;
  }

  @ExposedSet(name = "elt")
  public void setElt(PyObject elt) {
    this.elt = AstAdapters.py2expr(elt);
  }

  private java.util.List<comprehension> generators;

  public java.util.List<comprehension> getInternalGenerators() {
    return generators;
  }

  @ExposedGet(name = "generators")
  public PyObject getGenerators() {
    return new AstList(generators, AstAdapters.comprehensionAdapter);
  }

  @ExposedSet(name = "generators")
  public void setGenerators(PyObject generators) {
    this.generators = AstAdapters.py2comprehensionList(generators);
  }

  private final static PyUnicode[] fields = new PyUnicode[] { new PyUnicode("elt"), new PyUnicode("generators") };

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

  public ListComp(PyType subType) {
    super(subType);
  }

  public ListComp() {
    this(TYPE);
  }

  @ExposedNew
  @ExposedMethod
  public void ListComp___init__(PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("ListComp", args, keywords,
        new String[] { "elt", "generators", "lineno", "col_offset" }, 2, true);
    setElt(ap.getPyObject(0, Py.None));
    setGenerators(ap.getPyObject(1, Py.None));
    int lin = ap.getInt(2, -1);
    if (lin != -1) {
      setLineno(lin);
    }

    int col = ap.getInt(3, -1);
    if (col != -1) {
      setLineno(col);
    }

  }

  public ListComp(PyObject elt, PyObject generators) {
    setElt(elt);
    setGenerators(generators);
  }

  public ListComp(Token token, expr elt, java.util.List<comprehension> generators) {
    super(token);
    this.elt = elt;
    addChild(elt);
    this.generators = generators;
    if (generators == null) {
      this.generators = new ArrayList<comprehension>();
    }
    for (PythonTree t : this.generators) {
      addChild(t);
    }
  }

  public ListComp(Integer ttype, Token token, expr elt, java.util.List<comprehension> generators) {
    super(ttype, token);
    this.elt = elt;
    addChild(elt);
    this.generators = generators;
    if (generators == null) {
      this.generators = new ArrayList<comprehension>();
    }
    for (PythonTree t : this.generators) {
      addChild(t);
    }
  }

  public ListComp(PythonTree tree, expr elt, java.util.List<comprehension> generators) {
    super(tree);
    this.elt = elt;
    addChild(elt);
    this.generators = generators;
    if (generators == null) {
      this.generators = new ArrayList<comprehension>();
    }
    for (PythonTree t : this.generators) {
      addChild(t);
    }
  }

  @ExposedGet(name = "repr")
  public String toString() {
    return "ListComp";
  }

  public String toStringTree() {
    StringBuffer sb = new StringBuffer("ListComp(");
    sb.append("elt=");
    sb.append(dumpThis(elt));
    sb.append(",");
    sb.append("generators=");
    sb.append(dumpThis(generators));
    sb.append(",");
    sb.append(")");
    return sb.toString();
  }

  public <R> R accept(VisitorIF<R> visitor) throws Exception {
    return visitor.visitListComp(this);
  }

  public void traverse(VisitorIF<?> visitor) throws Exception {
    if (elt != null)
      elt.accept(visitor);
    if (generators != null) {
      for (PythonTree t : generators) {
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