// Copyright (c) Corporation for National Research Initiatives
package org.kamalyes.jython3.core;

@Untraversable
public class PySingleton extends PyObject {
  private String name;

  public PySingleton(PyType type, String name) {
    super(type);
    this.name = name;
  }

  public PySingleton(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }
}
