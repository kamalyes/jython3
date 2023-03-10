package org.kamalyes.jython3.modules.jffi;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedGet;

public abstract class BasePointer extends PyObject implements Pointer {

  BasePointer(PyType subtype) {
    super(subtype);
  }

  @ExposedGet(name = "address")
  public PyObject address() {
    return Py.newInteger(getMemory().getAddress());
  }

  @Override
  public boolean __bool__() {
    return !getMemory().isNull();
  }

  @Override
  public PyObject __int__() {
    return address();
  }
}
