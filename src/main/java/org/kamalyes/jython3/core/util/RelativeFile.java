/* Copyright (c) 2007 Jython Developers */
package org.kamalyes.jython3.core.util;

import java.io.File;

import org.kamalyes.jython3.core.PySystemState;

/**
 * A java.io.File whose initial path is resolved relative to the
 * current SystemState's current working directory.
 *
 * @author Philip Jenvey
 */
public class RelativeFile extends File {

  public RelativeFile(String pathname) {
    super(PySystemState.getPathLazy(pathname));
  }

  public RelativeFile(String parent, String child) {
    super(PySystemState.getPathLazy(parent), child);
  }
}
