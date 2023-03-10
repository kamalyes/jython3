package org.kamalyes.jython3.modules._io;

import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "_io.BufferedRandom")
public class PyBufferedRandom extends PyObject {

  public static final PyType TYPE = PyType.fromClass(PyBufferedRandom.class);

  public PyBufferedRandom() {
    super(TYPE);
  }
}
