package org.kamalyes.jython3.modules._multiprocessing;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.expose.ExposedModule;
import org.kamalyes.jython3.expose.ModuleInit;

@ExposedModule
public class _multiprocessing {
  @ModuleInit
  public static void classDictInit(PyObject dict) {
    dict.__setitem__("SemLock", PySemLock.TYPE);
    dict.__setitem__("sem_unlock", Py.NotImplemented);
  }

}
