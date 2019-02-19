package net.unicon.lti.ltl.core.lib.exception;

public interface LtlErrorCode {

  // Yes, we know about Spring's `HttpStatus` enum, but trying not to bind to Spring libs from
  // our core. Also, is intentionally a short list... just adding entries as we need them.
  enum HttpStatus {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404);

    private int code;

    private HttpStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }

  static String formatCode(String prefix, String code) {
    return String.format("%s.%s", prefix, code);
  }

  String getCodespace();

  String getCode();

  String getMessage();

  int getHttpStatus();
}
