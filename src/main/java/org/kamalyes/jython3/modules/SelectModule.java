package org.kamalyes.jython3.modules;

import org.kamalyes.jython3.core.ClassDictInit;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyUnicode;

/**
 * Created by isaiah on 6/19/16.
 */
public class SelectModule implements ClassDictInit {
  public static void classDictInit(PyObject dict) {
    dict.__setitem__("__name__", new PyUnicode("select"));

    // hide
    dict.__setitem__("classDictInit", null);
  }
}
