package org.kamalyes.jython3.modules.cjkcodecs;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.expose.ExposedFunction;
import org.kamalyes.jython3.expose.ExposedModule;

import java.nio.charset.Charset;

@ExposedModule
public class _codecs_hk {
  private static Charset BIG5HKSCS = Charset.forName("big5hkscs");

  @ExposedFunction
  public static PyObject getcodec(String name) {
    switch (name) {
      case "big5hkscs":
        return new PyMultibyteCodec(BIG5HKSCS);
      default:
        throw Py.LookupError("no such codec is supported");
    }
  }
}
