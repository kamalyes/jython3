package org.kamalyes.jython3.modules.cjkcodecs;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.expose.ExposedFunction;
import org.kamalyes.jython3.expose.ExposedModule;

import java.nio.charset.Charset;

@ExposedModule(name = "_codecs_cn")
public class _codecs_cn {
  private static Charset GB2312 = Charset.forName("gb2312");
  private static Charset GBK = Charset.forName("gbk");
  private static Charset GB18030 = Charset.forName("gb18030");

  @ExposedFunction
  public static PyObject getcodec(String name) {
    switch (name) {
      case "gb2312":
        return new PyMultibyteCodec(GB2312);
      case "gb18030":
        return new PyMultibyteCodec(GB18030);
      case "gbk":
        return new PyMultibyteCodec(GBK);
      default:
        throw Py.LookupError("no such codec is supported");
    }
  }
}
