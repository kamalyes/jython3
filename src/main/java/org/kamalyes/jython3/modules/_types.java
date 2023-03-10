package org.kamalyes.jython3.modules;

import org.kamalyes.jython3.core.ClassDictInit;
import org.kamalyes.jython3.core.CompilerFlags;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyBuiltinCallable;
import org.kamalyes.jython3.core.PyBuiltinMethod;
import org.kamalyes.jython3.core.PyFunction;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyTableCode;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.expose.ExposedClassMethod;
import org.kamalyes.jython3.expose.ExposedFunction;
import org.kamalyes.jython3.expose.ExposedModule;

import static org.kamalyes.jython3.core.CodeFlag.*;

/**
 * Created by isaiah on 6/17/16.
 */
@ExposedModule
public class _types {

  /**
   * Convert regular generator function to a coroutine
   * this replaces the following logic in types.coroutine:
   * co_flags = func.__code__.co_flags
   * 
   * # Check if 'func' is a coroutine function.
   * # (0x180 == CO_COROUTINE | CO_ITERABLE_COROUTINE)
   * if co_flags & 0x180:
   * return func
   * 
   * # Check if 'func' is a generator function.
   * # (0x20 == CO_GENERATOR)
   * if co_flags & 0x20:
   * # TODO: Implement this in C.
   * co = func.__code__
   * func.__code__ = CodeType(
   * co.co_argcount, co.co_kwonlyargcount, co.co_nlocals,
   * co.co_stacksize,
   * co.co_flags | 0x100, # 0x100 == CO_ITERABLE_COROUTINE
   * co.co_code,
   * co.co_consts, co.co_names, co.co_varnames, co.co_filename,
   * co.co_name, co.co_firstlineno, co.co_lnotab, co.co_freevars,
   * co.co_cellvars)
   * return func
   * 
   * The problem is that PyTableCode cannot be initialized from Python, as there
   * are certain properties missing, i.e.
   * co_stacksize, co_lnotab etc
   * 
   * @param obj
   * @return function
   */
  @ExposedFunction
  public static PyObject coroutine(PyType self, PyObject obj) {
    if (!(obj instanceof PyFunction)) {
      throw Py.TypeError("coroutine expects a function");
    }
    PyFunction func = (PyFunction) obj;
    PyTableCode code = (PyTableCode) func.__code__;
    CompilerFlags flags = code.co_flags;
    if (flags.isFlagSet(CO_COROUTINE) || flags.isFlagSet(CO_ITERABLE_COROUTINE)) {
      return func;
    }
    if (flags.isFlagSet(CO_GENERATOR)) {
      flags.setFlag(CO_ITERABLE_COROUTINE);
      return func;
    }
    return Py.None;
  }
}
