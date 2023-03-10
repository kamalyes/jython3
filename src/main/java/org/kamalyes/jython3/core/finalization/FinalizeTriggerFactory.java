package org.kamalyes.jython3.core.finalization;

import org.kamalyes.jython3.core.PyObject;

/**
 * Reserved for use by JyNI.
 */
public interface FinalizeTriggerFactory {

  public FinalizeTrigger makeTrigger(PyObject toFinalize);
}
