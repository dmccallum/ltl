package net.unicon.lti.ltl.core.lib.exception;

import org.pac4j.core.exception.TechnicalException;

public class LtlException extends TechnicalException {

  public LtlException(String message) {
    super(message);
  }

  public LtlException(Throwable t) {
    super(t);
  }

  public LtlException(String message, Throwable t) {
    super(message, t);
  }
}
