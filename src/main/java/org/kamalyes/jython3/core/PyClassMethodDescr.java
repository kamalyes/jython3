/* Copyright (c) Jython Developers */
package org.kamalyes.jython3.core;

import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedType;

@Untraversable
@ExposedType(name = "classmethod_descriptor", isBaseType = false)
public class PyClassMethodDescr extends PyMethodDescr {

  public static final PyType TYPE = PyType.fromClass(PyClassMethodDescr.class);

  PyClassMethodDescr(PyType type, PyBuiltinCallable meth) {
    super(type, meth);
  }

  @Override
  public PyObject __get__(PyObject obj, PyObject type) {
    return classmethod_descriptor___get__(obj, type);
  }

  @ExposedMethod(defaults = "null")
  public PyObject classmethod_descriptor___get__(PyObject obj, PyObject type) {
    if (type == null || type == Py.None) {
      if (obj != null) {
        type = obj.getType();
      } else {
        throw Py.TypeError(String.format("descriptor '%s' for type '%s' needs either an "
            + " object or a type", name, dtype.fastGetName()));
      }
    } else if (!(type instanceof PyType)) {
      throw Py.TypeError(String.format("descriptor '%s' for type '%s' needs a type, not a"
          + " '%s' as arg 2", name, dtype.fastGetName(),
          type.getType().fastGetName()));
    }
    checkGetterType((PyType) type);
    return meth.bind(type);
  }

  @Override
  @ExposedGet(name = "__doc__")
  public String getDoc() {
    return super.getDoc();
  }
}
