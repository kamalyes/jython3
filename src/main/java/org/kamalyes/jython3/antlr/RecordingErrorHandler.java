/**
 * Copyright 2009, Google Inc.  All rights reserved.
 * Licensed to PSF under a Contributor Agreement.
 */
package org.kamalyes.jython3.antlr;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.kamalyes.jython3.antlr.ast.ErrorExpr;
import org.kamalyes.jython3.antlr.ast.ErrorMod;
import org.kamalyes.jython3.antlr.ast.ErrorSlice;
import org.kamalyes.jython3.antlr.ast.ErrorStmt;
import org.kamalyes.jython3.antlr.base.expr;
import org.kamalyes.jython3.antlr.base.mod;
import org.kamalyes.jython3.antlr.base.slice;
import org.kamalyes.jython3.antlr.base.stmt;

import java.util.ArrayList;
import java.util.List;

public class RecordingErrorHandler implements ErrorHandler {

  public List<RecognitionException> errs = new ArrayList<RecognitionException>();

  public void reportError(BaseRecognizer br, RecognitionException re) {
    errs.add(re);
  }

  public void recover(Lexer lex, RecognitionException re) {
    lex.recover(re);
  }

  public void recover(BaseRecognizer br, IntStream input, RecognitionException re) {
    br.recover(input, re);
  }

  public boolean mismatch(BaseRecognizer br, IntStream input, int ttype, BitSet follow) {
    return true;
  }

  public Object recoverFromMismatchedToken(BaseRecognizer br, IntStream input,
      int ttype, BitSet follow) {
    return null;
  }

  public expr errorExpr(PythonTree t) {
    return new ErrorExpr(t);
  }

  public mod errorMod(PythonTree t) {
    return new ErrorMod(t);
  }

  public slice errorSlice(PythonTree t) {
    return new ErrorSlice(t);
  }

  public stmt errorStmt(PythonTree t) {
    return new ErrorStmt(t);
  }

  public void error(String message, PythonTree t) {
    System.err.println(message);
  }
}
