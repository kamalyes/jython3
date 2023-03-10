package org.kamalyes.jython3.modules.zlib;

import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.BufferProtocol;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyBUF;
import org.kamalyes.jython3.core.PyBuffer;
import org.kamalyes.jython3.core.PyBytes;
import org.kamalyes.jython3.core.PyException;
import org.kamalyes.jython3.core.PyNewWrapper;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedType;

import java.util.zip.Deflater;

import static org.kamalyes.jython3.modules.zlib.ZlibModule.validateWbits;

@ExposedType(name = "zlib.Compress")
public class PyCompress extends PyObject {
  public static final PyType TYPE = PyType.fromClass(PyCompress.class);

  private Deflater deflater;

  protected PyCompress(int level, int strategy, PyObject dict) {
    deflater = new Deflater(level);
    deflater.setStrategy(strategy);
    if (dict != Py.None) {
      byte[] bytes = Py.unwrapBuffer(dict);
      deflater.setDictionary(bytes);
    }
  }

  @ExposedNew
  final static PyObject Compress_new(PyNewWrapper new_, boolean init, PyType subtype,
      PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("compressobj", args, keywords, new String[] { "level", "method",
        "wbits", "memLevel", "strategy", "zdict" }, 0);
    int level = ap.getInt(0, 6);
    if (level < -1 || level > 9) {
      throw Py.ValueError("Invalid initialization option");
    }
    int method = ap.getInt(1, ZlibModule.DEFLATED);
    int wbits = ap.getInt(2, ZlibModule.MAX_WBITS);
    int memLevel = ap.getInt(3, ZlibModule.DEF_MEM_LEVEL);
    validateWbits(wbits);
    int strategy = ap.getInt(4, ZlibModule.Z_DEFAULT_STRATEGY);
    PyObject zdict = ap.getPyObject(5, Py.None);
    return new PyCompress(level, strategy, zdict);
  }

  @ExposedMethod
  public final PyObject Compress_compress(PyObject data) {
    byte[] buf = Py.unwrapBuffer(data);
    deflater.setInput(buf);
    buf = new byte[ZlibModule.DEF_BUF_SIZE];
    int n = deflater.deflate(buf);
    return new PyBytes(buf, 0, n);
  }

  @ExposedMethod
  public final PyObject Compress_flush(PyObject[] args, String[] keywords) {
    if (deflater.finished()) {
      throw new PyException(ZlibModule.error, "Error -2 while flushing: inconsistent stream state");
    }
    ArgParser ap = new ArgParser("flush", args, keywords, "mode");
    int mode = ap.getInt(0, ZlibModule.Z_FINISH);
    byte[] buf = new byte[ZlibModule.DEF_BUF_SIZE];
    if (mode == ZlibModule.Z_FINISH) {
      mode = Deflater.FULL_FLUSH;
      deflater.finish();
    }
    int len = deflater.deflate(buf, 0, buf.length, mode);
    int totalLen = len;
    while (len == ZlibModule.DEF_BUF_SIZE) {
      byte[] tmp = buf;
      buf = new byte[tmp.length + ZlibModule.DEF_BUF_SIZE];
      System.arraycopy(tmp, 0, buf, 0, tmp.length);
      len = deflater.deflate(buf, tmp.length, ZlibModule.DEF_BUF_SIZE, mode);
      totalLen += len;
      if (len < ZlibModule.DEF_BUF_SIZE)
        break;
    }
    return new PyBytes(buf, 0, totalLen);
  }
  // def __init__(self, level=6, method=DEFLATED, wbits=MAX_WBITS,
  // memLevel=0, strategy=0):
}
