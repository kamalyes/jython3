package org.kamalyes.jython3.modules.cjkcodecs;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "MultibyteStreamReader")
public class PyMultibyteStreamReader extends PyObject {
  public static final PyType TYPE = PyType.fromClass(PyMultibyteStreamReader.class);

  @ExposedMethod
  public final PyObject MultibyteStreamReader_read(PyObject sizeobj) {
    return Py.None;
  }
}
