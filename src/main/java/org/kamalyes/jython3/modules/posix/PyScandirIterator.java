package org.kamalyes.jython3.modules.posix;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyIterator;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.expose.ExposedType;

import java.nio.file.Path;
import java.util.Iterator;

/**
 * Created by isaiah on 7/16/16.
 */
@ExposedType(name = "posix.ScandirIterator")
public class PyScandirIterator extends PyIterator {
  Iterator<Path> iter;

  public PyScandirIterator(Iterator<Path> iter) {
    this.iter = iter;
  }

  @Override
  public PyObject __next__() {
    if (!iter.hasNext())
      throw Py.StopIteration();
    return new PyDirEntry(iter.next());
  }
}
