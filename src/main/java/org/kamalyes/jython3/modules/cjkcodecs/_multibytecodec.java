package org.kamalyes.jython3.modules.cjkcodecs;

import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.expose.ExposedModule;
import org.kamalyes.jython3.expose.ModuleInit;

@ExposedModule
public class _multibytecodec {

  @ModuleInit
  public static void init(PyObject dict) {
    dict.__setitem__("MultibyteStreamReader", PyMultibyteStreamReader.TYPE);
    dict.__setitem__("MultibyteStreamWriter", PyMultibyteStreamWriter.TYPE);
    dict.__setitem__("MultibyteIncrementalDecoder", PyMultibyteIncrementalDecoder.TYPE);
    dict.__setitem__("MultibyteIncrementalEncoder", PyMultibyteIncrementalEncoder.TYPE);
  }
}
