package org.kamalyes.jython3.modules.jffi;

import org.kamalyes.jython3.core.PyObject;

/**
 *
 */
abstract public class NativeDataConverter {
  private final boolean referenceRequired;
  private final boolean postInvokeRequired;

  public NativeDataConverter() {
    this.referenceRequired = false;
    this.postInvokeRequired = false;
  }

  public NativeDataConverter(boolean referenceRequired, boolean postInvokeRequired) {
    this.referenceRequired = referenceRequired;
    this.postInvokeRequired = postInvokeRequired;
  }

  public final boolean isReferenceRequired() {
    return referenceRequired;
  }

  public final boolean isPostInvokeRequired() {
    return postInvokeRequired;
  }

  abstract public PyObject fromNative(PyObject obj);

  abstract public PyObject toNative(PyObject obj);

  abstract public NativeType nativeType();
}
