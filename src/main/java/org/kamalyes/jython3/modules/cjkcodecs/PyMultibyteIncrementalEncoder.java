package org.kamalyes.jython3.modules.cjkcodecs;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "MultibyteIncrementalEncoder")
public class PyMultibyteIncrementalEncoder extends PyObject {
  public static final PyType TYPE = PyType.fromClass(PyMultibyteIncrementalEncoder.class);

  @ExposedMethod
  public final PyObject MultibyteIncrementalEncoder_encode(PyObject[] args, String[] keywords) {
    return Py.None;
  }
}
