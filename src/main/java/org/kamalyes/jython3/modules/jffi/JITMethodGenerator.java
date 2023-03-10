package org.kamalyes.jython3.modules.jffi;

/**
 * 
 */
public interface JITMethodGenerator {

  public boolean isSupported(JITSignature signature);

  public void generate(AsmClassBuilder builder, String functionName, JITSignature signature);
}
