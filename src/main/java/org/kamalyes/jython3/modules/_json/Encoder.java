package org.kamalyes.jython3.modules._json;

import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyBytes;
import org.kamalyes.jython3.core.PyDictionary;
import org.kamalyes.jython3.core.PyFloat;
import org.kamalyes.jython3.core.PyInteger;
import org.kamalyes.jython3.core.PyLong;
import org.kamalyes.jython3.core.PyList;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyTuple;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.core.Traverseproc;
import org.kamalyes.jython3.core.Visitproc;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "_json.encoder", base = PyObject.class)
public class Encoder extends PyObject implements Traverseproc {

  public static final PyType TYPE = PyType.fromClass(Encoder.class);

  @ExposedGet
  public final String __module__ = "_json";

  final PyDictionary markers;
  final PyObject defaultfn;
  final PyObject encoder;
  final PyObject indent;
  final PyObject key_separator;
  final PyObject item_separator;
  final PyObject sort_keys;
  final boolean skipkeys;
  final boolean allow_nan;

  public Encoder(PyObject[] args, String[] kwds) {
    super();
    ArgParser ap = new ArgParser("encoder", args, kwds,
        new String[] { "markers", "default", "encoder", "indent",
            "key_separator", "item_separator", "sort_keys", "skipkeys", "allow_nan" });
    ap.noKeywords();
    PyObject m = ap.getPyObject(0);
    markers = m == Py.None ? null : (PyDictionary) m;
    defaultfn = ap.getPyObject(1);
    encoder = ap.getPyObject(2);
    indent = ap.getPyObject(3);
    key_separator = ap.getPyObject(4);
    item_separator = ap.getPyObject(5);
    sort_keys = ap.getPyObject(6);
    skipkeys = ap.getPyObject(7).__bool__();
    allow_nan = ap.getPyObject(8).__bool__();
  }

  public PyObject __call__(PyObject obj) {
    return __call__(obj, Py.Zero);
  }

  public PyObject __call__(PyObject obj, PyObject indent_level) {
    PyList rval = new PyList();
    encode_obj(rval, obj, 0);
    return rval;
  }

  private PyUnicode encode_float(PyObject obj) {
    /* Return the JSON representation of a PyFloat */
    double i = obj.asDouble();
    if (Double.isInfinite(i) || Double.isNaN(i)) {
      if (!allow_nan) {
        throw Py.ValueError("Out of range float values are not JSON compliant");
      }
      if (i == Double.POSITIVE_INFINITY) {
        return new PyUnicode("Infinity");
      } else if (i == Double.NEGATIVE_INFINITY) {
        return new PyUnicode("-Infinity");
      } else {
        return new PyUnicode("NaN");
      }
    }
    /* Use a better float format here? */
    return obj.__repr__();
  }

  private PyBytes encode_string(PyObject obj) {
    /* Return the JSON representation of a string */
    return (PyBytes) encoder.__call__(obj);
  }

  private PyObject checkCircularReference(PyObject obj) {
    PyObject ident = null;
    if (markers != null) {
      ident = Py.newInteger(Py.id(obj));
      if (markers.__contains__(ident)) {
        throw Py.ValueError("Circular reference detected");
      }
      markers.__setitem__(ident, obj);
    }
    return ident;
  }

  private void encode_obj(PyList rval, PyObject obj, int indent_level) {
    /* Encode Python object obj to a JSON term, rval is a PyList */
    if (obj == Py.None) {
      rval.append(new PyBytes("null"));
    } else if (obj == Py.True) {
      rval.append(new PyBytes("true"));
    } else if (obj == Py.False) {
      rval.append(new PyBytes("false"));
    } else if (obj instanceof PyBytes) {
      rval.append(encode_string(obj));
    } else if (obj instanceof PyInteger || obj instanceof PyLong) {
      rval.append(obj.__str__());
    } else if (obj instanceof PyFloat) {
      rval.append(encode_float(obj));
    } else if (obj instanceof PyList || obj instanceof PyTuple) {
      encode_list(rval, obj, indent_level);
    } else if (obj instanceof PyDictionary) {
      encode_dict(rval, (PyDictionary) obj, indent_level);
    } else {
      PyObject ident = checkCircularReference(obj);
      if (defaultfn == Py.None) {
        throw Py.TypeError(String.format(".80s is not JSON serializable", obj.__repr__()));
      }

      PyObject newobj = defaultfn.__call__(obj);
      encode_obj(rval, newobj, indent_level);
      if (ident != null) {
        markers.__delitem__(ident);
      }
    }
  }

  private void encode_dict(PyList rval, PyDictionary dct, int indent_level) {
    /* Encode Python dict dct a JSON term */
    if (dct.__len__() == 0) {
      rval.append(new PyBytes("{}"));
      return;
    }

    PyObject ident = checkCircularReference(dct);
    rval.append(new PyBytes("{"));

    /* TODO: C speedup not implemented for sort_keys */

    int idx = 0;
    for (PyObject key : dct.asIterable()) {
      PyUnicode kstr;

      if (key instanceof PyBytes) {
        kstr = (PyUnicode) ((PyBytes) key).decode();
      } else if (key instanceof PyUnicode) {
        kstr = (PyUnicode) key;
      } else if (key instanceof PyFloat) {
        kstr = encode_float(key);
      } else if (key instanceof PyInteger || key instanceof PyLong) {
        kstr = key.__str__();
      } else if (key == Py.True) {
        kstr = new PyUnicode("true");
      } else if (key == Py.False) {
        kstr = new PyUnicode("false");
      } else if (key == Py.None) {
        kstr = new PyUnicode("null");
      } else if (skipkeys) {
        continue;
      } else {
        throw Py.TypeError(String.format("keys must be a string: %.80s", key.__repr__()));
      }

      if (idx > 0) {
        rval.append(item_separator);
      }

      PyObject value = dct.__getitem__(key);
      PyBytes encoded = encode_string(kstr);
      rval.append(encoded);
      rval.append(key_separator);
      encode_obj(rval, value, indent_level);
      idx += 1;
    }

    if (ident != null) {
      markers.__delitem__(ident);
    }
    rval.append(new PyBytes("}"));
  }

  private void encode_list(PyList rval, PyObject seq, int indent_level) {
    PyObject ident = checkCircularReference(seq);
    rval.append(new PyBytes("["));

    int i = 0;
    for (PyObject obj : seq.asIterable()) {
      if (i > 0) {
        rval.append(item_separator);
      }
      encode_obj(rval, obj, indent_level);
      i++;
    }

    if (ident != null) {
      markers.__delitem__(ident);
    }
    rval.append(new PyBytes("]"));
  }

  /* Traverseproc implementation */
  @Override
  public int traverse(Visitproc visit, Object arg) {
    int retVal;
    if (markers != null) {
      retVal = visit.visit(markers, arg);
      if (retVal != 0) {
        return retVal;
      }
    }
    if (defaultfn != null) {
      retVal = visit.visit(defaultfn, arg);
      if (retVal != 0) {
        return retVal;
      }
    }
    if (encoder != null) {
      retVal = visit.visit(encoder, arg);
      if (retVal != 0) {
        return retVal;
      }
    }
    if (indent != null) {
      retVal = visit.visit(indent, arg);
      if (retVal != 0) {
        return retVal;
      }
    }
    if (key_separator != null) {
      retVal = visit.visit(key_separator, arg);
      if (retVal != 0) {
        return retVal;
      }
    }
    if (item_separator != null) {
      retVal = visit.visit(item_separator, arg);
      if (retVal != 0) {
        return retVal;
      }
    }
    return sort_keys != null ? visit.visit(sort_keys, arg) : 0;
  }

  @Override
  public boolean refersDirectlyTo(PyObject ob) {
    return ob != null && (ob == markers || ob == defaultfn
        || ob == encoder || ob == indent || ob == key_separator
        || ob == item_separator || ob == sort_keys);
  }
}
