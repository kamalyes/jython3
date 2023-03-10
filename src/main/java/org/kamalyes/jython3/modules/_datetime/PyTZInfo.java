package org.kamalyes.jython3.modules._datetime;

import org.kamalyes.jython3.core.PyNewWrapper;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "datetime.tzinfo")
public class PyTZInfo extends PyObject {
  public static final PyType TYPE = PyType.fromClass(PyTZInfo.class);

  public PyTZInfo() {
    super(TYPE);
  }

  public PyTZInfo(PyType subType) {
    super(subType);
  }

  @ExposedNew
  final static PyObject tzinfo_new(PyNewWrapper new_, boolean init, PyType subtype,
      PyObject[] args, String[] keywords) {
    return new PyTZInfo();
  }
}
