package net.unicon.lti.ltl.core.lib.exception;

import org.apache.commons.lang3.StringUtils;

public abstract class LtlCodedException extends LtlException {

  private static String normalizeMessageAndCode(String message, LtlErrorCode errorCode) {
    if (StringUtils.isBlank(message)) {
      return String.format("[%s]: %s", errorCode.getCode(), errorCode.getMessage());
    }
    return String.format("[%s]: %s", errorCode.getCode(), message);
  }

  private LtlErrorCode errorCode;

  public LtlCodedException(LtlErrorCode errorCode) {
    this(errorCode, null);
  }

  public LtlCodedException(LtlErrorCode errorCode, String message) {
    this(errorCode, message, null);
  }

  public LtlCodedException(LtlErrorCode errorCode, String message, Throwable t) {
    super(normalizeMessageAndCode(message, errorCode), t);
    this.errorCode = errorCode;
  }

  public LtlErrorCode getErrorCode() {
    return errorCode;
  }
}
