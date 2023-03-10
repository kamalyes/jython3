package org.kamalyes.jython3.modules._datetime;

import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.PyLong;
import org.kamalyes.jython3.core.PyNewWrapper;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.expose.ExposedClassMethod;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedType;
import org.kamalyes.jython3.modules.time.TimeModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@ExposedType(name = "datetime.datetime")
public class PyDateTime extends PyDate {
  public static final PyType TYPE = PyType.fromClass(PyDateTime.class);

  private LocalTime time;
  private ZoneOffset timezone;

  public PyDateTime(PyType type) {
    super(type);
    LocalDateTime now = LocalDateTime.now();
    date = now.toLocalDate();
    time = now.toLocalTime();
  }

  public PyDateTime(PyType subType, int year, int month, int day) {
    super(subType, year, month, day);
  }

  @ExposedNew
  final static PyObject datetime_new(PyNewWrapper new_, boolean init, PyType subtype,
      PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("datetime", args, keywords, "year", "month", "day", "hour", "minute", "second");
    int year = ap.getInt(0);
    int month = ap.getInt(1);
    int day = ap.getInt(2);
    return new PyDateTime(TYPE, year, month, day);
  }

  @ExposedMethod
  public final PyObject datetime_time() {
    return new PyTime(time, timezone);
  }

  @ExposedMethod
  public final PyObject datetime_ctime() {
    return new PyUnicode(LocalDateTime.of(date, time).format(TimeModule.DEFAULT_FORMAT_PY));
  }

  @ExposedMethod
  public final PyObject datetime_hour() {
    return new PyLong(time.getHour());
  }

  @ExposedClassMethod
  public static final PyObject datetime_now(PyType type) {
    return new PyDateTime(type);
  }
}
