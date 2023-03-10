package org.kamalyes.jython3.modules._multiprocessing;

import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.BuiltinDocs;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyNewWrapper;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyStringMap;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.core.Untraversable;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedType;

import java.util.concurrent.Semaphore;

/**
 * Created by isaiah on 6/16/16.
 */
@Untraversable
@ExposedType(name = "_multiprocessing.SemLock", base = PyObject.class)
public class PySemLock extends PyObject {
  public static int SEM_VALUE_MAX = Integer.MAX_VALUE;
  public static PyType TYPE = PyType.fromClass(PySemLock.class);

  private Semaphore semaphore;
  private int kind;
  private int value;
  private int maxvalue;
  private String name;
  private boolean unlink;

  public PySemLock(int value) {
    super(TYPE);
    semaphore = new Semaphore(value);
  }

  @ExposedGet
  protected PyStringMap __dict__ = new PyStringMap();

  @Override
  public PyStringMap fastGetDict() {
    return __dict__;
  }

  @ExposedNew
  final static PyObject SemLock___new__(PyNewWrapper new_, boolean init, PyType subtype,
      PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("SemLock", args, keywords, "kind", "value", "maxvalue", "name", "unlink");
    return new PySemLock(SEM_VALUE_MAX);
  }

  @ExposedMethod(doc = BuiltinDocs.SemLock_acquire_doc)
  final PyObject SemLock_acquire() {
    try {
      semaphore.acquire();
    } catch (InterruptedException e) {
      return Py.False;
    }
    return Py.True;
  }

  @ExposedMethod(doc = BuiltinDocs.SemLock_release_doc)
  final PyObject SemLock_release() {
    semaphore.release();
    return Py.None;
  }
}
