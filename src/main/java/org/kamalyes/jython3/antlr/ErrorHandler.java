package org.kamalyes.jython3.antlr;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.kamalyes.jython3.antlr.base.expr;
import org.kamalyes.jython3.antlr.base.mod;
import org.kamalyes.jython3.antlr.base.slice;
import org.kamalyes.jython3.antlr.base.stmt;

interface ErrorHandler {
  void reportError(BaseRecognizer br, RecognitionException re);

  void recover(BaseRecognizer br, IntStream input, RecognitionException re);

  void recover(Lexer lex, RecognitionException re);

  /**
   * @return True if the caller should handle the mismatch
   */
  boolean mismatch(BaseRecognizer br, IntStream input, int ttype, BitSet follow)
      throws RecognitionException;

  /**
   * @return null if the caller should handle the mismatch
   */
  Object recoverFromMismatchedToken(BaseRecognizer br, IntStream input, int ttype, BitSet follow)
      throws RecognitionException;

  // expr, mod, slice, stmt
  expr errorExpr(PythonTree t);

  mod errorMod(PythonTree t);

  slice errorSlice(PythonTree t);

  stmt errorStmt(PythonTree t);

  // Exceptions
  void error(String message, PythonTree t);
}
