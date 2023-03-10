package org.kamalyes.jython3.expose;

import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;

/**
 * Contains the basic information needed to construct a builtin Python type.
 */
public interface TypeBuilder {

  public String getName();

  public PyObject getDict(PyType type);

  public Class getTypeClass();

  public Class getBase();

  public boolean getIsBaseType();

  public String getDoc();
}
