package org.kamalyes.jython3.modules._collections;

import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.expose.ExposedModule;
import org.kamalyes.jython3.expose.ModuleInit;

/**
 * Collections - This module adds the ability to use high performance data
 * structures.
 * - deque: ordered collection accessible from endpoints only
 * - defaultdict: dict subclass with a default value factory
 */
@ExposedModule(name = "_collections")
public class Collections {

  @ModuleInit
  public static void classDictInit(PyObject dict) {
    dict.__setitem__("deque", PyDeque.TYPE);
    dict.__setitem__("defaultdict", PyDefaultDict.TYPE);
    // xxx OrderedDict missing
  }
}
