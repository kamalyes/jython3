
package org.kamalyes.jython3.modules.jffi;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyList;
import org.kamalyes.jython3.core.PyNewWrapper;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.core.Traverseproc;
import org.kamalyes.jython3.core.Visitproc;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedType;

@ExposedType(name = "jffi.StructLayout", base = CType.class)
public class StructLayout extends CType.Custom {
  public static final PyType TYPE = PyType.fromClass(StructLayout.class);
  static {
    TYPE.fastGetDict().__setitem__("Field", Field.TYPE);
    TYPE.fastGetDict().__setitem__("ScalarField", ScalarField.TYPE);
  }

  private final Map<Object, Field> fieldMap;
  private final List<Field> fields;

  StructLayout(Field[] fields, com.kenai.jffi.Type struct, MemoryOp op) {
    super(NativeType.STRUCT, struct, op);
    Map<Object, Field> m = new HashMap<Object, Field>(fields.length);
    for (Field f : fields) {
      m.put(f.name, f);
      m.put(f.name.toString(), f);
    }
    this.fieldMap = m;
    this.fields = Collections.unmodifiableList(Arrays.asList(fields));
  }

  @ExposedType(name = "jffi.StructLayout.Field", base = PyObject.class)
  public static class Field extends PyObject implements Traverseproc {
    public static final PyType TYPE = PyType.fromClass(Field.class);
    @ExposedGet
    final CType ctype;

    @ExposedGet
    final int offset;

    final PyObject name;

    final MemoryOp op;

    Field(PyObject name, CType ctype, int offset, MemoryOp op) {
      this.name = name;
      this.ctype = ctype;
      this.offset = offset;
      this.op = op;
    }

    Field(PyObject name, CType ctype, int offset) {
      this(name, ctype, offset, ctype.getMemoryOp());
    }

    private static org.kamalyes.jython3.modules.jffi.Pointer asPointer(PyObject obj) {
      if (!(obj instanceof org.kamalyes.jython3.modules.jffi.Pointer)) {
        throw Py.TypeError("expected pointer");
      }

      return (org.kamalyes.jython3.modules.jffi.Pointer) obj;
    }

    @Override
    public PyObject __get__(PyObject obj, PyObject type) {
      return Field___get__(obj, type);
    }

    @Override
    public void __set__(PyObject obj, PyObject value) {
      Field___set__(obj, value);
    }

    @ExposedMethod
    public PyObject Field___get__(PyObject obj, PyObject type) {
      return op.get(asPointer(obj).getMemory(), offset);
    }

    @ExposedMethod
    public void Field___set__(PyObject obj, PyObject value) {
      op.put(asPointer(obj).getMemory(), offset, value);
    }

    @ExposedMethod(names = { "get" })
    PyObject get(PyObject obj) {
      return op.get(asPointer(obj).getMemory(), offset);
    }

    @ExposedMethod(names = { "set" })
    PyObject set(PyObject obj, PyObject value) {

      op.put(asPointer(obj).getMemory(), offset, value);

      return value;
    }

    /* Traverseproc implementation */
    @Override
    public int traverse(Visitproc visit, Object arg) {
      if (name != null) {
        int retVal = visit.visit(name, arg);
        if (retVal != 0) {
          return retVal;
        }
      }
      return ctype != null ? visit.visit(ctype, arg) : 0;
    }

    @Override
    public boolean refersDirectlyTo(PyObject ob) {
      return ob != null && (ob == name || ob == ctype);
    }
  }

  /**
   * We enclose any references to the jffi Type class in a lazily-loaded class
   * so the exposed types processor does not crash when jffi can't load the
   * native stub lib.
   */
  private static final class StructUtil {

    public static final StructLayout newStructLayout(Field[] fields, boolean isUnion) {
      com.kenai.jffi.Type[] fieldTypes = new com.kenai.jffi.Type[fields.length];

      for (int i = 0; i < fields.length; ++i) {
        fieldTypes[i] = Util.jffiType(fields[i].ctype);
      }

      com.kenai.jffi.Type jffiType = isUnion
          ? new com.kenai.jffi.Union(fieldTypes)
          : new com.kenai.jffi.Struct(fieldTypes);

      return new StructLayout(fields, jffiType, MemoryOp.INVALID);
    }
  }

  @ExposedNew
  public static PyObject StructLayout_new(PyNewWrapper new_, boolean init, PyType subtype,
      PyObject[] args, String[] keywords) {

    ArgParser ap = new ArgParser("__init__", args, keywords, new String[] { "fields", "union" }, 1);

    if (!(ap.getPyObject(0) instanceof PyList)) {
      throw Py.TypeError("expected list of jffi.StructLayout.Field");
    }

    PyList pyFields = (PyList) ap.getPyObject(0);
    Field[] fields = new Field[pyFields.size()];

    for (int i = 0; i < fields.length; ++i) {
      PyObject pyField = pyFields.pyget(i);
      if (!(pyField instanceof Field)) {
        throw Py.TypeError(String.format("element %d of field list is not an instance of jffi.StructLayout.Field", i));
      }

      fields[i] = (Field) pyField;
    }

    return StructUtil.newStructLayout(fields, ap.getPyObject(1, Py.False).__bool__());
  }

  @ExposedType(name = "jffi.StructLayout.ScalarField", base = Field.class)
  public static class ScalarField extends Field {
    public static final PyType TYPE = PyType.fromClass(ScalarField.class);

    public ScalarField(PyObject name, CType ctype, int offset) {
      super(name, ctype, offset);
    }

    @ExposedNew
    public static PyObject ScalarField_new(PyNewWrapper new_, boolean init, PyType subtype,
        PyObject[] args, String[] keywords) {
      ArgParser ap = new ArgParser("__init__", args, keywords, new String[] { "name", "type", "offset" });

      return new ScalarField(ap.getPyObject(0), CType.typeOf(ap.getPyObject(1)), ap.getInt(2));
    }
  }

  Field getField(PyObject name) {
    return fieldMap.get(name);
  }

  Field getField(String name) {
    return fieldMap.get(name);
  }

  List<Field> getFieldList() {
    return fields;
  }

  @Override
  public PyObject __getitem__(PyObject key) {
    StructLayout.Field f = getField(key);
    return f != null ? f : Py.None;
  }
}
