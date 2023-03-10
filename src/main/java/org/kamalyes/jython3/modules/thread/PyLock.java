// Copyright (c) Corporation for National Research Initiatives
package org.kamalyes.jython3.modules.thread;

import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.ContextManager;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.ThreadState;
import org.kamalyes.jython3.core.PyException;
import org.kamalyes.jython3.core.Untraversable;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedType;

@Untraversable
@ExposedType(name = "_thread.lock")
public class PyLock extends PyObject implements ContextManager {

  private boolean locked = false;

  public boolean acquire() {
    return acquire(true, -1);
  }

  @ExposedMethod(names = "acquire", defaults = { "true", "-1" })
  public synchronized boolean acquire(boolean blocking, int timeout) {
    if (blocking) {
      while (locked) {
        try {
          wait();
        } catch (InterruptedException e) {
          System.err.println("Interrupted thread");
        }
      }
      locked = true;
      return true;
    } else {
      if (locked) {
        return false;
      } else {
        locked = true;
        return true;
      }
    }
  }

  @ExposedMethod(names = "release")
  public synchronized void release() {
    if (locked) {
      locked = false;
      notifyAll();
    } else {
      throw Py.ValueError("lock not acquired");
    }
  }

  @ExposedMethod
  public boolean lock_locked() {
    return locked;
  }

  @ExposedMethod
  public PyObject lock___enter__() {
    return __enter__(Py.getThreadState());
  }

  @Override
  public PyObject __enter__(ThreadState ts) {
    acquire();
    return this;
  }

  @ExposedMethod
  public boolean lock___exit__(PyObject type, PyObject value, PyObject traceback) {
    return __exit__(Py.getThreadState(), null);
  }

  @Override
  public boolean __exit__(ThreadState ts, PyException exception) {
    release();
    return false;
  }
}
