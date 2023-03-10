package org.kamalyes.jython3.modules.cjkcodecs;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.expose.ExposedFunction;
import org.kamalyes.jython3.expose.ExposedModule;

import java.nio.charset.Charset;

@ExposedModule(name = "_codecs_tw")
public class _codecs_tw {
  private static Charset BIG5 = Charset.forName("big5");
  private static Charset CP950 = Charset.forName("cp950");

  @ExposedFunction
  public static PyObject getcodec(String name) {
    switch (name) {
      case "big5":
        return new PyMultibyteCodec(BIG5);
      case "cp950":
        return new PyMultibyteCodec(CP950);
      default:
        throw Py.LookupError("no such codec is supported");
    }
  }
}
