package org.kamalyes.jython3.modules.jffi;

import com.kenai.jffi.CallingConvention;
import com.kenai.jffi.Platform;

/**
 *
 */
final class FastLongMethodGenerator extends AbstractNumericMethodGenerator {
  private static final int MAX_PARAMETERS = getMaximumFastLongParameters();

  private static final String[] signatures = buildSignatures(long.class, MAX_PARAMETERS);

  private static final String[] methodNames = {
      "invokeVrL", "invokeLrL", "invokeLLrL", "invokeLLLrL", "invokeLLLLrL", "invokeLLLLLrL", "invokeLLLLLLrL"
  };

  String getInvokerMethodName(JITSignature signature) {

    final int parameterCount = signature.getParameterCount();

    if (parameterCount <= MAX_PARAMETERS && parameterCount <= methodNames.length) {
      return methodNames[parameterCount];

    } else {
      throw new IllegalArgumentException("invalid fast-long parameter count: " + parameterCount);
    }
  }

  String getInvokerSignature(int parameterCount) {
    if (parameterCount <= MAX_PARAMETERS && parameterCount <= signatures.length) {
      return signatures[parameterCount];
    }
    throw new IllegalArgumentException("invalid fast-long parameter count: " + parameterCount);
  }

  final Class getInvokerIntType() {
    return long.class;
  }

  public boolean isSupported(JITSignature signature) {
    final int parameterCount = signature.getParameterCount();

    if (!signature.getCallingConvention().equals(CallingConvention.DEFAULT) || parameterCount > MAX_PARAMETERS) {
      return false;
    }

    final Platform platform = Platform.getPlatform();

    if (platform.getOS().equals(Platform.OS.WINDOWS)) {
      return false;
    }

    // Only supported on amd64 arches
    if (!platform.getCPU().equals(Platform.CPU.X86_64)) {
      return false;
    }

    for (int i = 0; i < parameterCount; i++) {
      if (!isFastLongParameter(platform, signature.getParameterType(i))) {
        return false;
      }
    }

    return isFastLongResult(platform, signature.getResultType());
  }

  final static int getMaximumFastLongParameters() {
    try {
      com.kenai.jffi.Invoker.class.getDeclaredMethod("invokeLLLLLLrL", com.kenai.jffi.Function.class,
          long.class, long.class, long.class, long.class, long.class, long.class);
      return 6;
    } catch (Throwable t) {
      return -1;
    }
  }

  private static boolean isFastLongType(Platform platform, NativeType type) {
    switch (type) {
      case BOOL:
      case BYTE:
      case UBYTE:
      case SHORT:
      case USHORT:
      case INT:
      case UINT:
      case LONG:
      case ULONG:
      case LONGLONG:
      case ULONGLONG:
      case POINTER:
        return true;

      default:
        return false;
    }
  }

  static boolean isFastLongResult(Platform platform, NativeType type) {
    switch (type) {
      case VOID:
        return true;

      case POINTER:
      case STRING:
        return platform.addressSize() == 64;

      default:
        return isFastLongType(platform, type);
    }
  }

  static boolean isFastLongParameter(Platform platform, NativeType type) {
    switch (type) {
      case POINTER:
      case BUFFER_IN:
      case BUFFER_OUT:
      case BUFFER_INOUT:
        return platform.addressSize() == 64;

      default:
        return isFastLongType(platform, type);
    }
  }
}
