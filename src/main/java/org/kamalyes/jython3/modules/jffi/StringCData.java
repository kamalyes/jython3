
package org.kamalyes.jython3.modules.jffi;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyNewWrapper;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedClassMethod;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedSet;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "jffi.StringCData", base = CData.class)
public class StringCData extends AbstractMemoryCData {
  public static final PyType TYPE = PyType.fromClass(StringCData.class);

  public StringCData(PyType pytype, CType ctype, DirectMemory m) {
    super(pytype, ctype, m);
  }

  @ExposedNew
  public static PyObject StringCData_new(PyNewWrapper new_, boolean init, PyType subtype,
      PyObject[] args, String[] keywords) {

    // No args == create NULL pointer
    if (args.length == 0) {
      return new StringCData(subtype, CType.typeOf(subtype), NullMemory.INSTANCE);
    }

    byte[] str = args[0].asString().getBytes();
    DirectMemory m = AllocatedNativeMemory.allocate(str.length + 1, false);
    m.putZeroTerminatedByteArray(0, str, 0, str.length);
    return new StringCData(subtype, CType.typeOf(subtype), m);
  }

  @ExposedClassMethod(names = { "from_address" })
  public static final PyObject from_address(PyType subtype, PyObject address) {

    DirectMemory m = Util.getMemoryForAddress(address);
    StringCData cdata = new StringCData(subtype, CType.typeOf(subtype), m.getMemory(0));
    cdata.setReferenceMemory(m);

    return cdata;
  }

  @ExposedGet(name = "value")
  public PyObject getValue() {
    Memory m = getMemory();
    return !m.isNull()
        ? Py.newString(new String(m.getZeroTerminatedByteArray(0)))
        : Py.None;
  }

  @ExposedSet(name = "value")
  public void setValue(PyObject value) {
    byte[] str = value.asString().getBytes();
    DirectMemory m = AllocatedNativeMemory.allocate(str.length + 1, false);
    m.putZeroTerminatedByteArray(0, str, 0, str.length);
    this.memory = m;
    if (hasReferenceMemory()) {
      getReferenceMemory().putAddress(0, m);
    }
  }

  @Override
  public final String toString() {
    return getType().getName() + "(" + getValue().toString() + ")";
  }

  @Override
  public String asString() {
    Memory m = getMemory();
    return !m.isNull()
        ? new String(m.getZeroTerminatedByteArray(0))
        : null;
  }

}
