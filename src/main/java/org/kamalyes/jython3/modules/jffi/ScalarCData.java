
package org.kamalyes.jython3.modules.jffi;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyFloat;
import org.kamalyes.jython3.core.PyInteger;
import org.kamalyes.jython3.core.PyLong;
import org.kamalyes.jython3.core.PyNewWrapper;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyObject.ConversionException;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedClassMethod;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedSet;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "jffi.ScalarCData", base = CData.class)
public class ScalarCData extends CData {
  public static final PyType TYPE = PyType.fromClass(ScalarCData.class);
  static {
    // TYPE.fastGetDict().__setitem__("in_dll", new InDll());
  }
  private PyObject value;

  @ExposedNew
  public static PyObject ScalarCData_new(PyNewWrapper new_, boolean init, PyType subtype,
      PyObject[] args, String[] keywords) {

    ScalarCData cdata = new ScalarCData(subtype, getScalarType(subtype));

    // If an initial value was supplied, use it, else default to zero
    cdata.setValue(args.length > 0 ? args[0] : Py.newInteger(0));

    return cdata;
  }

  @ExposedClassMethod(names = { "from_address" })
  public static final PyObject from_address(PyType subtype, PyObject address) {
    return new ScalarCData(subtype, getScalarType(subtype), Util.getMemoryForAddress(address));
  }

  static final CType.Builtin getScalarType(PyType subtype) {
    PyObject jffi_type = subtype.__getattr__("_jffi_type");

    if (!(jffi_type instanceof CType.Builtin)) {
      throw Py.TypeError("invalid _jffi_type for " + subtype.getName());
    }
    return (CType.Builtin) jffi_type;
  }

  ScalarCData(PyType pytype, CType.Builtin ctype) {
    super(pytype, ctype);
  }

  ScalarCData(PyType pytype, CType.Builtin ctype, DirectMemory m) {
    super(pytype, ctype, m);
  }

  protected final void initReferenceMemory(Memory m) {
    getMemoryOp().put(m, 0, value);
  }

  @ExposedGet(name = "value")
  public PyObject getValue() {

    // If native memory has been allocated, read the value from there
    if (hasReferenceMemory()) {
      return getMemoryOp().get(getReferenceMemory(), 0);
    }

    return value != null ? value : Py.None;
  }

  @ExposedSet(name = "value")
  public void setValue(PyObject value) {
    this.value = value;
    // If native memory has been allocated, sync the value to memory
    if (hasReferenceMemory()) {
      getMemoryOp().put(getReferenceMemory(), 0, value);
    }
  }

  @Override
  public int asInt() {
    return getValue().asInt();
  }

  @Override
  public long asLong(int index) throws ConversionException {
    return getValue().asLong(index);
  }

  public long asLong() {
    return getValue().asLong();
  }

  @ExposedMethod
  @Override
  public PyObject __int__() {
    return getValue().__int__();
  }

  @ExposedMethod
  @Override
  public PyFloat __float__() {
    return getValue().__float__();
  }

  @Override
  public final String toString() {
    return getType().getName() + "(" + getValue().toString() + ")";
  }
}
