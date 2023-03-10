/*
 * Copyright 2000 Finn Bock
 *
 * This program contains material copyrighted by:
 * Copyright (c) 1997-2000 by Secret Labs AB.  All rights reserved.
 *
 * This version of the SRE library can be redistributed under CNRI's
 * Python 1.6 license.  For any other use, please contact Secret Labs
 * AB (info@pythonware.com).
 *
 * Portions of this engine have been developed in cooperation with
 * CNRI.  Hewlett-Packard provided funding for 1.6 integration and
 * other compatibility work.
 */
package org.kamalyes.jython3.modules;

import org.kamalyes.jython3.core.ArgParser;
import org.kamalyes.jython3.core.PyObject;
import org.kamalyes.jython3.core.PyUnicode;
import org.kamalyes.jython3.expose.ExposedConst;
import org.kamalyes.jython3.expose.ExposedFunction;
import org.kamalyes.jython3.expose.ExposedModule;
import org.kamalyes.jython3.modules.sre.PatternObject;
import org.kamalyes.jython3.modules.sre.SRE_STATE;

@ExposedModule
public class _sre {
  @ExposedConst
  public static final int MAGIC = SRE_STATE.SRE_MAGIC;

  // probably the right number for Jython since we are UTF-16.
  @ExposedConst
  public static final int MAXREPEAT = Character.MAX_VALUE;

  @ExposedConst
  public static final int MAXGROUPS = Integer.MAX_VALUE;

  // workaround the fact that H, I types are unsigned, but we are not really using
  // them as such
  // TODO: May not be the right size, but I suspect it is -- see sre_compile.py
  @ExposedConst
  public static final int CODESIZE = 4;

  @ExposedFunction
  public static PatternObject compile(PyObject[] args, String[] keywords) {
    ArgParser ap = new ArgParser("compile", args, keywords,
        "pattern", "flags", "code", "groups", "groupindex", "indexgroup");
    PyObject pattern = ap.getPyObject(0);
    int flags = ap.getInt(1);
    PyObject code = ap.getPyObject(2);
    int groups = ap.getInt(3);
    PyObject groupindex = ap.getPyObject(4);
    PyObject indexgroup = ap.getPyObject(5);
    long[] ccode = new long[code.__len__()];
    int i = 0;
    for (PyObject item : code.asIterable()) {
      ccode[i++] = item.asLong();
    }
    return new PatternObject(pattern, flags, ccode, groups, groupindex, indexgroup);
  }

  @ExposedFunction
  public static int getcodesize() {
    return CODESIZE;
  }

  @ExposedFunction
  public static int getlower(PyObject ch, PyObject flags) {
    return SRE_STATE.getlower(ch.asInt(), flags.asInt());
  }
}
