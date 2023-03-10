// Autogenerated AST node
package org.kamalyes.jython3.antlr.ast;

import org.antlr.runtime.Token;
import org.kamalyes.jython3.antlr.AST;
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
import org.kamalyes.jython3.core.Visitproc;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedSet;
import org.kamalyes.jython3.expose.ExposedType;

import java.util.ArrayList;

@ExposedType(name = "_ast.arguments", base = AST.class)
public class arguments extends PythonTree {
  public static final PyType TYPE = PyType.fromClass(arguments.class);
  private java.util.List<arg> args;

  public java.util.List<arg> getInternalArgs() {
    return args;
  }

  @ExposedGet(name = "args")
  public PyObject getArgs() {
    return new AstList(args, AstAdapters.argAdapter);
  }

  @ExposedSet(name = "args")
  public void setArgs(PyObject args) {
    this.args = AstAdapters.py2argList(args);
  }

  private arg vararg;

  public arg getInternalVararg() {
    return vararg;
  }

  @ExposedGet(name = "vararg")
  public PyObject getVararg() {
    return vararg;
  }

  @ExposedSet(name = "vararg")
  public void setVararg(PyObject vararg) {
    this.vararg = AstAdapters.py2arg(vararg);
  }

  private java.util.List<arg> kwonlyargs;

  public java.util.List<arg> getInternalKwonlyargs() {
    return kwonlyargs;
  }

  @ExposedGet(name = "kwonlyargs")
  public PyObject getKwonlyargs() {
    return new AstList(kwonlyargs, AstAdapters.argAdapter);
  }

  @ExposedSet(name = "kwonlyargs")
  public void setKwonlyargs(PyObject kwonlyargs) {
    this.kwonlyargs = AstAdapters.py2argList(kwonlyargs);
  }

  private java.util.List<expr> kw_defaults;

  public java.util.List<expr> getInternalKw_defaults() {
    return kw_defaults;
  }

  @ExposedGet(name = "kw_defaults")
  public PyObject getKw_defaults() {
    return new AstList(kw_defaults, AstAdapters.exprAdapter);
  }

  @ExposedSet(name = "kw_defaults")
  public void setKw_defaults(PyObject kw_defaults) {
    this.kw_defaults = AstAdapters.py2exprList(kw_defaults);
  }

  private arg kwarg;

  public arg getInternalKwarg() {
    return kwarg;
  }

  @ExposedGet(name = "kwarg")
  public PyObject getKwarg() {
    return kwarg;
  }

  @ExposedSet(name = "kwarg")
  public void setKwarg(PyObject kwarg) {
    this.kwarg = AstAdapters.py2arg(kwarg);
  }

  private java.util.List<expr> defaults;

  public java.util.List<expr> getInternalDefaults() {
    return defaults;
  }

  @ExposedGet(name = "defaults")
  public PyObject getDefaults() {
    return new AstList(defaults, AstAdapters.exprAdapter);
  }

  @ExposedSet(name = "defaults")
  public void setDefaults(PyObject defaults) {
    this.defaults = AstAdapters.py2exprList(defaults);
  }

  private final static PyUnicode[] fields = new PyUnicode[] { new PyUnicode("args"), new PyUnicode("vararg"),
      new PyUnicode("kwonlyargs"),
      new PyUnicode("kw_defaults"), new PyUnicode("kwarg"), new PyUnicode("defaults") };

  @ExposedGet(name = "_fields")
  public PyUnicode[] get_fields() {
    return fields;
  }

  private final static PyUnicode[] attributes = new PyUnicode[0];

  @ExposedGet(name = "_attributes")
  public PyUnicode[] get_attributes() {
    return attributes;
  }

  public arguments(PyType subType) {
    super(subType);
  }

  public arguments() {
    this(TYPE);
  }

  @ExposedNew
  @ExposedMethod
  public void arguments___init__(PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("arguments", args, keywords,
        new String[] { "args", "vararg", "kwonlyargs", "kw_defaults", "kwarg", "defaults" }, 6, true);
    setArgs(ap.getPyObject(0, Py.None));
    setVararg(ap.getPyObject(1, Py.None));
    setKwonlyargs(ap.getPyObject(2, Py.None));
    setKw_defaults(ap.getPyObject(3, Py.None));
    setKwarg(ap.getPyObject(4, Py.None));
    setDefaults(ap.getPyObject(5, Py.None));
  }

  public arguments(PyObject args, PyObject vararg, PyObject kwonlyargs, PyObject kw_defaults,
      PyObject kwarg, PyObject defaults) {
    setArgs(args);
    setVararg(vararg);
    setKwonlyargs(kwonlyargs);
    setKw_defaults(kw_defaults);
    setKwarg(kwarg);
    setDefaults(defaults);
  }

  public arguments(Token token, java.util.List<arg> args, arg vararg, java.util.List<arg> kwonlyargs,
      java.util.List<expr> kw_defaults, arg kwarg, java.util.List<expr> defaults) {
    super(token);
    this.args = args;
    if (args == null) {
      this.args = new ArrayList<arg>();
    }
    for (PythonTree t : this.args) {
      addChild(t);
    }
    this.vararg = vararg;
    this.kwonlyargs = kwonlyargs;
    if (kwonlyargs == null) {
      this.kwonlyargs = new ArrayList<arg>();
    }
    for (PythonTree t : this.kwonlyargs) {
      addChild(t);
    }
    this.kw_defaults = kw_defaults;
    if (kw_defaults == null) {
      this.kw_defaults = new ArrayList<expr>();
    }
    for (PythonTree t : this.kw_defaults) {
      addChild(t);
    }
    this.kwarg = kwarg;
    this.defaults = defaults;
    if (defaults == null) {
      this.defaults = new ArrayList<expr>();
    }
    for (PythonTree t : this.defaults) {
      addChild(t);
    }
  }

  public arguments(Integer ttype, Token token, java.util.List<arg> args, arg vararg,
      java.util.List<arg> kwonlyargs, java.util.List<expr> kw_defaults, arg kwarg,
      java.util.List<expr> defaults) {
    super(ttype, token);
    this.args = args;
    if (args == null) {
      this.args = new ArrayList<arg>();
    }
    for (PythonTree t : this.args) {
      addChild(t);
    }
    this.vararg = vararg;
    this.kwonlyargs = kwonlyargs;
    if (kwonlyargs == null) {
      this.kwonlyargs = new ArrayList<arg>();
    }
    for (PythonTree t : this.kwonlyargs) {
      addChild(t);
    }
    this.kw_defaults = kw_defaults;
    if (kw_defaults == null) {
      this.kw_defaults = new ArrayList<expr>();
    }
    for (PythonTree t : this.kw_defaults) {
      addChild(t);
    }
    this.kwarg = kwarg;
    this.defaults = defaults;
    if (defaults == null) {
      this.defaults = new ArrayList<expr>();
    }
    for (PythonTree t : this.defaults) {
      addChild(t);
    }
  }

  public arguments(PythonTree tree, java.util.List<arg> args, arg vararg, java.util.List<arg> kwonlyargs,
      java.util.List<expr> kw_defaults, arg kwarg, java.util.List<expr> defaults) {
    super(tree);
    this.args = args;
    if (args == null) {
      this.args = new ArrayList<arg>();
    }
    for (PythonTree t : this.args) {
      addChild(t);
    }
    this.vararg = vararg;
    this.kwonlyargs = kwonlyargs;
    if (kwonlyargs == null) {
      this.kwonlyargs = new ArrayList<arg>();
    }
    for (PythonTree t : this.kwonlyargs) {
      addChild(t);
    }
    this.kw_defaults = kw_defaults;
    if (kw_defaults == null) {
      this.kw_defaults = new ArrayList<expr>();
    }
    for (PythonTree t : this.kw_defaults) {
      addChild(t);
    }
    this.kwarg = kwarg;
    this.defaults = defaults;
    if (defaults == null) {
      this.defaults = new ArrayList<expr>();
    }
    for (PythonTree t : this.defaults) {
      addChild(t);
    }
  }

  @ExposedGet(name = "repr")
  public String toString() {
    return "arguments";
  }

  public String toStringTree() {
    StringBuffer sb = new StringBuffer("arguments(");
    sb.append("args=");
    sb.append(dumpThis(args));
    sb.append(",");
    sb.append("vararg=");
    sb.append(dumpThis(vararg));
    sb.append(",");
    sb.append("kwonlyargs=");
    sb.append(dumpThis(kwonlyargs));
    sb.append(",");
    sb.append("kw_defaults=");
    sb.append(dumpThis(kw_defaults));
    sb.append(",");
    sb.append("kwarg=");
    sb.append(dumpThis(kwarg));
    sb.append(",");
    sb.append("defaults=");
    sb.append(dumpThis(defaults));
    sb.append(",");
    sb.append(")");
    return sb.toString();
  }

  public <R> R accept(VisitorIF<R> visitor) throws Exception {
    traverse(visitor);
    return null;
  }

  public void traverse(VisitorIF<?> visitor) throws Exception {
    if (args != null) {
      for (PythonTree t : args) {
        if (t != null)
          t.accept(visitor);
      }
    }
    if (vararg != null)
      vararg.accept(visitor);
    if (kwonlyargs != null) {
      for (PythonTree t : kwonlyargs) {
        if (t != null)
          t.accept(visitor);
      }
    }
    if (kw_defaults != null) {
      for (PythonTree t : kw_defaults) {
        if (t != null)
          t.accept(visitor);
      }
    }
    if (kwarg != null)
      kwarg.accept(visitor);
    if (defaults != null) {
      for (PythonTree t : defaults) {
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

  /* Traverseproc implementation */
  @Override
  public int traverse(Visitproc visit, Object arg) {
    int retVal = super.traverse(visit, arg);
    if (retVal != 0) {
      return retVal;
    }
    if (args != null) {
      for (PyObject ob : args) {
        if (ob != null) {
          retVal = visit.visit(ob, arg);
          if (retVal != 0) {
            return retVal;
          }
        }
      }
    }
    if (defaults != null) {
      for (PyObject ob : defaults) {
        if (ob != null) {
          retVal = visit.visit(ob, arg);
          if (retVal != 0) {
            return retVal;
          }
        }
      }
    }
    return 0;
  }

  @Override
  public boolean refersDirectlyTo(PyObject ob) {
    if (ob == null) {
      return false;
    } else if (args != null && args.contains(ob)) {
      return true;
    } else if (defaults != null && defaults.contains(ob)) {
      return true;
    } else {
      return super.refersDirectlyTo(ob);
    }
  }
}