package org.kamalyes.jython3.compiler;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.kamalyes.jython3.antlr.base.mod;
import org.kamalyes.jython3.core.BytecodeLoader;
import org.kamalyes.jython3.core.CompilerFlags;
import org.kamalyes.jython3.core.Py;
import org.kamalyes.jython3.core.PyCode;
import org.kamalyes.jython3.core.PythonCodeBundle;
import org.kamalyes.jython3.core.PythonCompiler;

public class LegacyCompiler implements PythonCompiler {

  public PythonCodeBundle compile(mod node, String name, String filename,
      boolean linenumbers, boolean printResults, CompilerFlags cflags) {
    return new LazyLegacyBundle(node, name, filename, linenumbers,
        printResults, cflags);
  }

  private static class LazyLegacyBundle implements PythonCodeBundle {

    private final mod node;
    private final String name;
    private final String filename;
    private final boolean linenumbers;
    private final boolean printResults;
    private final CompilerFlags cflags;
    private ByteArrayOutputStream ostream = null;

    public LazyLegacyBundle(mod node, String name, String filename,
        boolean linenumbers, boolean printResults, CompilerFlags cflags) {
      this.node = node;
      this.name = name;
      this.filename = filename;
      this.linenumbers = linenumbers;
      this.printResults = printResults;
      this.cflags = cflags;
    }

    public PyCode loadCode() throws Exception {
      return BytecodeLoader.makeCode(name, ostream().toByteArray(),
          filename);
    }

    public void writeTo(OutputStream stream) throws Exception {
      if (this.ostream != null) {
        stream.write(ostream.toByteArray());
      } else {
        Module.compile(node, stream, name, filename, linenumbers,
            printResults, cflags);
      }
    }

    private ByteArrayOutputStream ostream() throws Exception {
      if (ostream == null) {
        ostream = new ByteArrayOutputStream();
        Module.compile(node, ostream, name, filename, linenumbers,
            printResults, cflags);
      }
      return ostream;
    }

  }

}
