package net.unicon.lti.ltl.core.lib.util;

import com.nimbusds.jwt.JWTClaimsSet;
import java.util.Collection;
import java.util.Map;
import net.unicon.lti.ltl.core.lib.exception.LtlFieldValidationException;
import net.unicon.lti.ltl.core.lib.exception.LtlFieldValidationException.LtlMissingToolErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.pac4j.core.context.J2EContext;

public final class LtlUtils {

  public static void requireField(String fieldName, J2EContext context) {
    if (StringUtils.isBlank(context.getRequestParameter(fieldName))) {
      throw missingField(fieldName);
    }
  }

  public static void requireField(String fieldName, JWTClaimsSet claims) {
    Object fieldValue = claims.getClaim(fieldName);
    if (fieldValue == null) {
      throw missingField(fieldName);
    }
    if (fieldValue instanceof String) {
      if (StringUtils.isBlank((String) fieldValue)) {
        throw blankField(fieldName);
      }
    }
    if (fieldValue instanceof Collection) {
      if (((Collection<?>) fieldValue).isEmpty()) {
        throw blankField(fieldName);
      }
    }
    // TODO this also needs to check to see if we get a JSONObject back instead of a Map
    if (fieldValue instanceof Map) {
      if (((Map<?, ?>) fieldValue).isEmpty()) {
        throw blankField(fieldName);
      }
    }
  }

  public static LtlFieldValidationException missingField(String fieldName) {
    return new LtlFieldValidationException(fieldName, LtlMissingToolErrorCode.FIELD_MISSING);
  }

  public static LtlFieldValidationException blankField(String fieldName) {
    return new LtlFieldValidationException(fieldName, LtlMissingToolErrorCode.FIELD_BLANK);
  }

  public static String withTrailingSlash(String base) {
    return withTrailingString(base, "/");
  }

  public static String withoutTrailingSlash(String base) {
    return withoutTrailingString(base, "/");
  }

  public static String withTrailingString(String base, String toMatch) {
    if (StringUtils.isBlank(base)) {
      return toMatch;
    }
    if (base.endsWith(toMatch)) {
      return base;
    }
    return base + toMatch;
  }

  public static String withoutTrailingString(String base, String toMatch) {
    if (StringUtils.isBlank(base)) {
      return base;
    }
    if (base.endsWith(toMatch)) {
      return base.substring(0, base.length() - toMatch.length());
    }
    return base;
  }

  private LtlUtils() {
    // not intended for instantiation
  }
}
