package org.kamalyes.jython3.core;

import org.kamalyes.jython3.antlr.BaseParser;
import org.kamalyes.jython3.antlr.base.mod;

public enum CompileMode {
  eval {
    @Override
    mod dispatch(BaseParser parser) {
      return parser.parseExpression();
    }
  },
  single {
    @Override
    mod dispatch(BaseParser parser) {
      return parser.parseInteractive();
    }
  },
  exec {
    @Override
    mod dispatch(BaseParser parser) {
      return parser.parseModule();
    }
  };

  abstract mod dispatch(BaseParser parser);

  public static CompileMode getMode(String mode) {
    if (!mode.equals("exec") && !mode.equals("eval") && !mode.equals("single")) {
      throw Py.ValueError("compile() arg 3 must be 'exec' or 'eval' or 'single'");
    }
    return valueOf(mode);
  }
}
