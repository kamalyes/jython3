/* Copyright (c) 2007 Jython Developers */
package org.kamalyes.jython3.core.io;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;

/**
 * Jython file descriptor management.
 *
 * File descriptor objects in Jython are instances of RawIOBase.
 *
 * @author Philip Jenvey
 */
public class FileDescriptors {

  /**
   * Return the RawIOBase associated with the specified file descriptor.
   *
   * Raises a Python exception is the file descriptor is invalid
   *
   * @param fd
   *           a Jython file descriptor object
   * @return the RawIOBase associated with the file descriptor
   */
  public static RawIOBase get(PyObject fd) {
    return Py.tojava(fd, RawIOBase.class);
  }
}
