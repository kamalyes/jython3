package org.kamalyes.jython3.modules._socket;

import jnr.constants.platform.AddressFamily;
import jnr.constants.platform.ProtocolFamily;
import jnr.constants.platform.Sock;
import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyNewWrapper;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyTuple;
import org.kamalyes.jython3.core.PyType;
import org.kamalyes.jython3.expose.ExposedMethod;
import org.kamalyes.jython3.expose.ExposedNew;
import org.kamalyes.jython3.expose.ExposedType;

/**
 * Created by isaiah on 6/19/16.
 */
@ExposedType(name = "socket")
public class PySocket extends PyObject {
  public static PyType TYPE = PyType.fromClass(PySocket.class);

  private AddressFamily sock_family;
  private ProtocolFamily sock_proto;
  private Sock sock;

  public PySocket(AddressFamily family, Sock socket, ProtocolFamily proto) {
    super(TYPE);
    sock_family = family;
    sock = socket;
    sock_proto = proto;
  }

  @ExposedNew
  static final PyObject socket___new__(PyNewWrapper new_, boolean init, PyType subtype,
      PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("socket", args, keywords, "family", "type", "proto", "fileno");
    long af = ap.getInt(0, AddressFamily.AF_INET.intValue());
    long st = ap.getInt(1, Sock.SOCK_STREAM.intValue());
    int p = ap.getInt(2, 0);
    return new PySocket(AddressFamily.valueOf(af), Sock.valueOf(st), ProtocolFamily.valueOf(p));
  }

  @ExposedMethod()
  final PyObject socket_bind(PyObject address) {
    PyObject host, port;
    if (address instanceof PyTuple) {
      host = ((PyTuple) address).pyget(0);
      port = ((PyTuple) address).pyget(1);
    }
    return this;
  }
}
