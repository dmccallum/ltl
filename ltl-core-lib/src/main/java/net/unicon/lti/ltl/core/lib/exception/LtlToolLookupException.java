package net.unicon.lti.ltl.core.lib.exception;

public class LtlToolLookupException extends LtlCodedException {

  public enum LtlToolLookupErrorCode implements LtlErrorCode {
    TOOL_NOT_FOUND(HttpStatus.NOT_FOUND, "Missing tool configuration."),
    TOOL_NOT_FOUND_DURING_AUTHENTICATION(HttpStatus.FORBIDDEN, "Missing tool configuration."),
    TOO_MANY_TOOLS_FOUND_DURING_AUTHENTICATION(
        HttpStatus.FORBIDDEN, "Found too many tool configurations.");

    private static final String PREFIX = "TLKP";
    private HttpStatus httpStatusCode;
    private String message;
    private String code;

    LtlToolLookupErrorCode(HttpStatus httpStatusCode, String message) {
      this.httpStatusCode = httpStatusCode;
      this.message = message;
      this.code = LtlErrorCode.formatCode(PREFIX, name());
    }

    public String getCodespace() {
      return PREFIX;
    }

    public String getCode() {
      return code;
    }

    public String getMessage() {
      return message;
    }

    public int getHttpStatus() {
      return httpStatusCode.getCode();
    }
  }

  public LtlToolLookupException(LtlErrorCode errorCode) {
    super(errorCode);
  }

  public LtlToolLookupException(LtlErrorCode errorCode, String message) {
    super(errorCode, message);
  }

  public LtlToolLookupException(LtlErrorCode errorCode, String message, Throwable cause) {
    super(errorCode, message, cause);
  }
}
