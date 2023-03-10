
package org.kamalyes.jython3.modules.jffi;

import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.core.Untraversable;
import org.kamalyes.jython3.expose.ExposedType;

@Untraversable
@ExposedType(name = "jffi.ByReference", base = PyObject.class)
public final class ByReference extends PyObject implements Pointer {
  public static final PyType TYPE = PyType.fromClass(ByReference.class);

  private final DirectMemory memory;

  ByReference(CType componentType, DirectMemory memory) {
    super(TYPE);
    this.memory = memory;
  }

  public final DirectMemory getMemory() {
    return memory;
  }

  @Override
  public boolean __bool__() {
    return !getMemory().isNull();
  }
}
