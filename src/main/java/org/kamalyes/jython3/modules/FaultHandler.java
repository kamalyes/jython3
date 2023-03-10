package org.kamalyes.jython3.modules;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.expose.ExposedFunction;
import org.kamalyes.jython3.expose.ExposedModule;

/**
 * Created by isaiah on 7/15/16.
 */
@ExposedModule(name = "faulthandler")
public class FaultHandler {
  @ExposedFunction
  public static void dump_traceback(PyObject[] args, String[] keywords) {
  }

  @ExposedFunction
  public static void enable(PyObject[] args, String[] keywords) {
  }

  @ExposedFunction
  public static void disable() {
  }

  @ExposedFunction
  public static PyObject is_enabled() {
    return Py.False;
  }

  @ExposedFunction
  public static void dump_traceback_later(PyObject[] args, String[] keywords) {
  }

  @ExposedFunction
  public static void cancel_dump_traceback_later() {
  }

  @ExposedFunction
  public static void register(PyObject[] args, String[] keywords) {
  }

  @ExposedFunction
  public static void unregister(PyObject[] args, String[] keywords) {
  }
}
