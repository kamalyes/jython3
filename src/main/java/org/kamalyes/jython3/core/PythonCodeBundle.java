package org.kamalyes.jython3.core;

import java.io.OutputStream;

public interface PythonCodeBundle {

  PyCode loadCode() throws Exception;

  void writeTo(OutputStream stream) throws Exception;

}
