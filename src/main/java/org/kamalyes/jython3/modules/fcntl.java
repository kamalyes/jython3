package org.kamalyes.jython3.modules;

import jnr.constants.platform.Fcntl;
import jnr.posix.POSIX;
import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.ClassDictInit;
import org.kamalyes.jython3.core.PyLong;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.modules.posix.PosixModule;

/**
 * Created by isaiah on 6/17/16.
 */
public class fcntl implements ClassDictInit {
  private static POSIX posix = PosixModule.getPOSIX();

  public static void classDictInit(PyObject dict) {
    dict.__setitem__("__name__", new PyUnicode("fcntl"));
    for (Fcntl val : Fcntl.values()) {
      dict.__setitem__(val.name(), new PyLong(val.longValue()));
    }

    // hide from Python
    dict.__setitem__("classDictInit", null);
  }

  public static int fcntl(PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("fcntl", args, keywords, "fd", "cmd", "arg");
    PyObject fileDescriptor = ap.getPyObject(0);
    int cmd = ap.getInt(1);
    int fd = PosixModule.getFD(fileDescriptor).getIntFD();
    return posix.fcntl(fd, Fcntl.valueOf(cmd));
  }
}
