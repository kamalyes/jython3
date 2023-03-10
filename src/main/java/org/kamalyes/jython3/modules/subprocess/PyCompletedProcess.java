package org.kamalyes.jython3.modules.subprocess;

import com.google.common.io.ByteStreams;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyBytes;
import org.kamalyes.jython3.core.PyLong;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedGet;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedType;

import java.io.IOException;
import java.io.InputStream;

@ExposedType(name = "subprocess.CompletedProcess")
public class PyCompletedProcess extends PyObject {
  public static final PyType TYPE = PyType.fromClass(PyCompletedProcess.class);
  private Process proc;

  @ExposedGet
  public PyObject args;

  public PyCompletedProcess(PyObject args, Process proc) {
    super(TYPE);
    this.args = args;
    this.proc = proc;
  }

  @ExposedMethod
  public PyObject CompletedProcess_check_returncode() {
    if (proc.exitValue() != 0) {
      throw SubprocessModule.CalledProcessError();
    }
    return new PyLong(proc.exitValue());
  }

  @ExposedGet
  public PyObject returncode() {
    return new PyLong(proc.exitValue());
  }

  @ExposedGet(name = "stdout")
  public PyObject getStdout() {
    InputStream out = proc.getInputStream();
    byte[] buf = new byte[0];
    try {
      buf = ByteStreams.toByteArray(out);
      return new PyBytes(new String(buf));
    } catch (IOException e) {
      return Py.None;
    }
  }

  @ExposedGet(name = "stderr")
  public PyObject getStderr() {
    InputStream out = proc.getErrorStream();
    byte[] buf = new byte[0];
    try {
      buf = ByteStreams.toByteArray(out);
      return new PyBytes(new String(buf));
    } catch (IOException e) {
      return Py.None;
    }
  }
}
