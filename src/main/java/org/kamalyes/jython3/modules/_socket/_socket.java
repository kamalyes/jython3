package org.kamalyes.jython3.modules._socket;

import org.kamalyes.jython3.core.ClassDictInit;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyUnicode;

/**
 * Created by isaiah on 6/18/16.
 */
public class _socket implements ClassDictInit {
  public static void classDictInit(PyObject dict) {
    dict.__setitem__("__name__", new PyUnicode("_socket"));
    dict.__setitem__("socket", PySocket.TYPE);

    // hide
    dict.__setitem__("classDictInit", null);
  }

}
