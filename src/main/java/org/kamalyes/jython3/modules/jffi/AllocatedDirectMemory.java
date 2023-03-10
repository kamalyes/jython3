
package org.kamalyes.jython3.modules.jffi;

public interface AllocatedDirectMemory extends DirectMemory {
  public void free();

  public void setAutoRelease(boolean autorelease);
}
