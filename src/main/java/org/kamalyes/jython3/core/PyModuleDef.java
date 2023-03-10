package org.kamalyes.jython3.core;

import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedType;

/**
 * Created by isaiah on 7/1/16.
 */
@ExposedType(name = "moduledef")
public class PyModuleDef extends PyObject {
  public static final PyType TYPE = PyType.fromClass(PyModuleDef.class);

  @ExposedGet
  public String name;

  public PyModuleDef(String name) {
    super(TYPE);
    this.name = name;
  }
}
