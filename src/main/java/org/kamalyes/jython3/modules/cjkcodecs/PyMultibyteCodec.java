package org.kamalyes.jython3.modules.cjkcodecs;

import org.kamalyes.jython3.core.BufferProtocol;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyBUF;
import org.kamalyes.jython3.core.PyBuffer;
import org.kamalyes.jython3.core.PyBytes;
import org.kamalyes.jython3.core.PyLong;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyTuple;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedType;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@ExposedType(name = "MultibyteCodec")
public class PyMultibyteCodec extends PyObject {
  public static final PyType TYPE = PyType.fromClass(PyMultibyteCodec.class);
  private Charset charset;

  public PyMultibyteCodec(Charset charset) {
    super(TYPE);
    this.charset = charset;
  }

  @ExposedMethod
  public PyObject MultibyteCodec_encode(PyObject o) {
    if (o instanceof PyUnicode) {
      String s = o.toString();
      ByteBuffer buf = charset.encode(s);
      return new PyTuple(new PyBytes(buf), new PyLong(s.length()));
    }
    throw Py.TypeError("expected a str object");
  }

  @ExposedMethod
  public PyObject MultibyteCodec_decode(PyObject o) {
    if (o instanceof BufferProtocol) {
      PyBuffer buf = ((BufferProtocol) o).getBuffer(PyBUF.FULL_RO);
      return new PyTuple(new PyUnicode(charset.decode(buf.getNIOByteBuffer())), new PyLong(o.__len__()));
    }
    throw Py.TypeError("expected a bytes object");
  }
}
