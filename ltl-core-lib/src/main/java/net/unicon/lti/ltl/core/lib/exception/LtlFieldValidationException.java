package net.unicon.lti.ltl.core.lib.exception;

import org.apache.commons.lang3.StringUtils;

public class LtlFieldValidationException extends LtlCodedException {

  public enum LtlMissingToolErrorCode implements LtlErrorCode {
    FIELD_MISSING("Field must be present"),
    FIELD_BLANK("Field must not be blank"),
    FIELD_MALFORMED("Field not formatted correctly"),
    FIELD_TOO_LONG("Field value is too long"),
    FIELD_TYPE_UNEXPECTED("Field has unexpected type"),
    FIELD_INVALID("Field value is invalid");

    private static final String PREFIX = "IVLD";
    private String message;
    private String code;

    LtlMissingToolErrorCode(String message) {
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
      return LtlErrorCode.HttpStatus.BAD_REQUEST.getCode();
    }
  }

  private static String buildMessage(String fieldPath, String validationDetail) {
    // super will take care of mixing in the error code
    if (StringUtils.isNotBlank(validationDetail)) {
      return String.format("Field: [%s], Detail: [%s]", fieldPath, validationDetail);
    } else {
      return String.format("Field: [%s]");
    }
  }

  private final String fieldPath;
  private final String validationDetail;

  public LtlFieldValidationException(String fieldPath, LtlMissingToolErrorCode validation) {
    this(fieldPath, validation, null, null);
  }

  public LtlFieldValidationException(
      String fieldPath, LtlMissingToolErrorCode validation, String validationDetail) {
    this(fieldPath, validation, validationDetail, null);
  }

  public LtlFieldValidationException(
      String fieldPath,
      LtlMissingToolErrorCode validation,
      String validationDetail,
      Throwable cause) {
    super(validation, buildMessage(fieldPath, validationDetail), cause);
    this.fieldPath = fieldPath;
    this.validationDetail = validationDetail;
  }

  public String getFieldPath() {
    return fieldPath;
  }

  public String getValidationDetail() {
    return validationDetail;
  }
}
