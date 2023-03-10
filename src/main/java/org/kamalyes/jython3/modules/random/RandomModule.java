package org.kamalyes.jython3.modules.random;

import org.kamalyes.jython3.core.ClassDictInit;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;

public class RandomModule implements ClassDictInit {

  private RandomModule() {
  }

  public static void classDictInit(PyObject dict) {
    dict.invoke("clear");
    dict.__setitem__("Random", PyRandom.TYPE);
    dict.__setitem__("__name__", Py.newString("_random"));
  }
}
