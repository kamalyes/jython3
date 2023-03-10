package org.kamalyes.jython3.modules.cjkcodecs;

import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "MultibyteIncrementalDecoder")
public class PyMultibyteIncrementalDecoder extends PyObject {
  public static final PyType TYPE = PyType.fromClass(PyMultibyteIncrementalDecoder.class);

  @ExposedMethod
  public final PyObject MultibyteIncrementalDecoder_decode(PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("decode", args, keywords, "input", "final");
    PyObject input = ap.getPyObject(0);
    boolean final_ = ap.getPyObject(1, Py.False).__bool__();
    return Py.None;
  }
}
