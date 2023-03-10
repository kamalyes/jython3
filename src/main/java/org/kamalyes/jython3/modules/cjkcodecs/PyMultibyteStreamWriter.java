package org.kamalyes.jython3.modules.cjkcodecs;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedType;

/**
 * Created by isaiah on 8/15/16.
 */
@ExposedType(name = "MultibyteStreamWriter")
public class PyMultibyteStreamWriter extends PyObject {
  public static final PyType TYPE = PyType.fromClass(PyMultibyteStreamWriter.class);

  @ExposedMethod
  public final PyObject MultibyteStreamWriter_write(PyObject strobj) {
    return Py.None;
  }
}
