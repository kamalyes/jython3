package org.kamalyes.jython3.modules._datetime;

import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.CompareOp;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyNewWrapper;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedType;

import java.time.ZoneOffset;

@ExposedType(name = "datetime.timezone")
public class PyTimezone extends PyTZInfo {
  public static final PyType TYPE = PyType.fromClass(PyTimezone.class);

  private String name;
  private PyTimeDelta delta;

  public PyTimezone(ZoneOffset offset) {
    this(TYPE, new PyTimeDelta(0, offset.compareTo(ZoneOffset.UTC), 0), offset.toString());
  }

  public PyTimezone(PyType subType, PyTimeDelta delta, String name) {
    super(subType);
    this.delta = delta;
    this.name = name;
  }

  @ExposedNew
  final static PyObject timezone_new(PyNewWrapper new_, boolean init, PyType subtype,
      PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("timezone", args, keywords, "utcoffset", "name");
    PyObject offset = ap.getPyObject(0);
    String name = ap.getString(1, null);
    if (!(offset instanceof PyTimeDelta)) {
      throw Py.TypeError(String.format("timezone() argument 1 must be datetime, not %s", offset.getType().getName()));
    }
    return new PyTimezone(subtype, (PyTimeDelta) offset, name);
  }

  @ExposedGet(name = "utcoffset")
  public int timezone_utcoffset() {
    ZoneOffset offset = ZoneOffset.ofHours((int) delta.toDuration().toHours());
    return offset.compareTo(ZoneOffset.UTC);
  }

  @ExposedMethod
  public PyObject timezone_dst(PyObject datetime) {
    if (!(datetime == Py.None || datetime instanceof PyDateTime)) {
      throw Py.TypeError(String.format(
          "dst(dt) argument must be a datetime instance or None, not %s", datetime.getType().getName()));
    }
    return Py.None;
  }

  @ExposedMethod
  public String timezone_tzname(PyObject datetime) {
    if (!(datetime == Py.None || datetime instanceof PyDateTime)) {
      throw Py.TypeError(String.format(
          "tzname(dt) argument must be a datetime instance or None, not %s", datetime.getType().getName()));
    }
    return name;
  }

  public PyUnicode __str__() {
    return new PyUnicode(name);
  }

  @Override
  public String toString() {
    if (name == null) {
      return String.format("%s(%s)", getType().getName(), delta);
    }
    return String.format("%s(%s, '%s')", getType().getName(), delta, name);
  }

  @Override
  public PyObject richCompare(PyObject other, CompareOp op) {
    if (other instanceof PyTimezone) {
      return delta.richCompare(((PyTimezone) other).delta, op);
    }
    return op.neq();
  }
}
