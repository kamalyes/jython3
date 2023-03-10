package org.kamalyes.jython3.modules;

import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyTuple;
import org.kamalyes.jython3.core.stringlib.FieldNameIterator;
import org.kamalyes.jython3.core.stringlib.MarkupIterator;
import org.kamalyes.jython3.expose.ExposedFunction;
import org.kamalyes.jython3.expose.ExposedModule;

@ExposedModule(doc = "string helper module")
public class _string {
  @ExposedFunction(doc = "parse the argument as a format string")
  public static PyObject formatter_parser(PyObject str) {
    return new MarkupIterator(str.toString());
  }

  @ExposedFunction(doc = "split the argument as a field name")
  public static PyObject formatter_field_name_split(PyObject str) {
    FieldNameIterator iterator = new FieldNameIterator(str.toString(), false);
    return new PyTuple(iterator.pyHead(), iterator);
  }
}
