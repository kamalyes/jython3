package org.kamalyes.jython3.expose.generate;

import org.objectweb.asm.Type;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyBoolean;
import org.kamalyes.jython3.core.PyBuiltinCallable;
import org.kamalyes.jython3.core.PyBuiltinMethod;
import org.kamalyes.jython3.core.PyBuiltinMethodNarrow;
import org.kamalyes.jython3.core.PyDataDescr;
import org.kamalyes.jython3.core.PyException;
import org.kamalyes.jython3.core.PyFloat;
import org.kamalyes.jython3.core.PyInteger;
import org.kamalyes.jython3.core.PyLong;
import org.kamalyes.jython3.core.PyModule;
import org.kamalyes.jython3.core.PyNewWrapper;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyBytes;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.core.ThreadState;
import org.kamalyes.jython3.expose.ExposeAsSuperclass;
import org.kamalyes.jython3.expose.ExposedClassMethod;
import org.kamalyes.jython3.expose.ExposedConst;
import org.kamalyes.jython3.expose.ExposedDelete;
import org.kamalyes.jython3.expose.ExposedFunction;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedModule;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedSet;
import org.kamalyes.jython3.expose.ExposedType;
import org.kamalyes.jython3.expose.ModuleInit;
import org.kamalyes.jython3.expose.TypeBuilder;

/**
 * Type objects used by exposed generation.
 */
public interface PyTypes {

  // Core Jython types
  public static final Type PYOBJ = Type.getType(PyObject.class);

  public static final Type APYOBJ = Type.getType(PyObject[].class);

  public static final Type PYTYPE = Type.getType(PyType.class);

  public static final Type PYMODULE = Type.getType(PyModule.class);

  public static final Type ASSUPER = Type.getType(ExposeAsSuperclass.class);

  public static final Type PYEXCEPTION = Type.getType(PyException.class);

  public static final Type PY = Type.getType(Py.class);

  public static final Type PYBYTES = Type.getType(PyBytes.class);

  public static final Type PYSTR = Type.getType(PyUnicode.class);

  public static final Type PYBOOLEAN = Type.getType(PyBoolean.class);

  public static final Type PYLONG = Type.getType(PyLong.class);

  public static final Type PYFLOAT = Type.getType(PyFloat.class);

  public static final Type PYNEWWRAPPER = Type.getType(PyNewWrapper.class);

  public static final Type BUILTIN_METHOD = Type.getType(PyBuiltinMethod.class);

  public static final Type ABUILTIN_METHOD = Type.getType(PyBuiltinMethod[].class);

  public static final Type BUILTIN_METHOD_NARROW = Type.getType(PyBuiltinMethodNarrow.class);

  public static final Type BUILTIN_FUNCTION = Type.getType(PyBuiltinCallable.class);

  public static final Type ABUILTIN_FUNCTION = Type.getType(PyBuiltinCallable[].class);

  public static final Type DATA_DESCR = Type.getType(PyDataDescr.class);

  public static final Type ADATA_DESCR = Type.getType(PyDataDescr[].class);

  public static final Type BUILTIN_INFO = Type.getType(PyBuiltinCallable.Info.class);

  public static final Type THREAD_STATE = Type.getType(ThreadState.class);

  // Exposer Jython types
  public static final Type EXPOSED_TYPE = Type.getType(ExposedType.class);

  public static final Type EXPOSED_MODULE = Type.getType(ExposedModule.class);

  public static final Type EXPOSED_METHOD = Type.getType(ExposedMethod.class);

  public static final Type EXPOSED_FUNCTION = Type.getType(ExposedFunction.class);

  public static final Type MODULE_INIT = Type.getType(ModuleInit.class);

  public static final Type EXPOSED_CLASS_METHOD = Type.getType(ExposedClassMethod.class);

  public static final Type EXPOSED_NEW = Type.getType(ExposedNew.class);

  public static final Type EXPOSED_GET = Type.getType(ExposedGet.class);

  public static final Type EXPOSED_SET = Type.getType(ExposedSet.class);

  public static final Type EXPOSED_DELETE = Type.getType(ExposedDelete.class);

  public static final Type EXPOSED_CONST = Type.getType(ExposedConst.class);

  public static final Type TYPEBUILDER = Type.getType(TypeBuilder.class);

  // Java types
  public static final Type OBJECT = Type.getType(Object.class);

  public static final Type STRING = Type.getType(String.class);

  public static final Type ASTRING = Type.getType(String[].class);

  public static final Type STRING_BUILDER = Type.getType(StringBuilder.class);

  public static final Type CLASS = Type.getType(Class.class);

  // Primitives
  public static final Type BYTE = Type.BYTE_TYPE;

  public static final Type SHORT = Type.SHORT_TYPE;

  public static final Type CHAR = Type.CHAR_TYPE;

  public static final Type INT = Type.INT_TYPE;

  public static final Type VOID = Type.VOID_TYPE;

  public static final Type BOOLEAN = Type.BOOLEAN_TYPE;

  // module init method descriptor
  String INIT_DESCRIPTOR = Type.getMethodDescriptor(VOID, PYOBJ);
}
