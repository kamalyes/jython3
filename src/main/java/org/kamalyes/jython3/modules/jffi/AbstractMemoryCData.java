
package org.kamalyes.jython3.modules.jffi;

import org.kamalyes.jython3.core.PyType;

public abstract class AbstractMemoryCData extends CData implements Pointer {
  protected DirectMemory memory;

  AbstractMemoryCData(PyType subtype, CType type, DirectMemory memory) {
    super(subtype, type);
    this.memory = memory;
  }

  @Override
  public boolean __bool__() {
    return !getMemory().isNull();
  }

  protected void initReferenceMemory(Memory m) {
    m.putAddress(0, memory);
  }

  public final DirectMemory getMemory() {
    return hasReferenceMemory() ? getReferenceMemory().getMemory(0) : memory;
  }
}
