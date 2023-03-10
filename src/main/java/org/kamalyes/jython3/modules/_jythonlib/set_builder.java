/* Copyright (c) Jython Developers */
package org.kamalyes.jython3.modules._jythonlib;

import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PySet;
import org.kamalyes.jython3.core.PySetDerived;
import org.kamalyes.jython3.core.PyType;

import java.util.Set;

/* Support building PySet objects with arbitrary backing Set objects
 * Uses a factory for efficiency.
 *
 * See the very similar dict_builder for more insight. But note that we do not
 * impose the restriction that the set be concurrent, although this is generally
 * what we would want.
 */

public class set_builder extends PyObject {

  public static final PyType TYPE = PyType.fromClass(set_builder.class);
  private final PyObject factory;
  private final PyType set_type;

  public set_builder(PyObject factory) {
    super();
    this.factory = factory;
    this.set_type = null;
  }

  public set_builder(PyObject factory, PyType set_type) {
    super();
    this.factory = factory;
    this.set_type = set_type;
  }

  public PyObject __call__(PyObject iterable) {
    Set backing_set = (Set) (factory.__call__().__tojava__(Set.class));
    if (set_type == null) {
      return new PySet(backing_set, iterable == Py.None ? null : iterable);
    } else {
      return new PySetDerived(set_type, backing_set, iterable == Py.None ? null : iterable);
    }
  }

}
