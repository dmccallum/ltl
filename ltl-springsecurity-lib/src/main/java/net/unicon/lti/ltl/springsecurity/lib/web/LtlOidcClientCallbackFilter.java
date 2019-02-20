package net.unicon.lti.ltl.springsecurity.lib.web;

import net.unicon.lti.ltl.springsecurity.lib.util.AntRequestPathMatcher;
import org.apache.commons.lang3.StringUtils;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.matching.Matcher;
import org.pac4j.springframework.security.web.CallbackFilter;

public class LtlOidcClientCallbackFilter extends CallbackFilter {

  private static final String DEFAULT_REQUEST_ANT_PATH_PATTERN =
      "/oauth2/oidc/lti/authorization/**";

  private Matcher requestMatcher;

  public LtlOidcClientCallbackFilter(Config config) {
    this(config, (String) null);
  }

  public LtlOidcClientCallbackFilter(Config config, String requestAntPathPattern) {
    // Special requestPathMatcher only needed if Client names are in the path rather than as a query
    // param, which we're currently doing to try to rule out problems with query param encoding on
    // the Platform side. Either way, there's a potential problem if the Platform expects return
    // URLs to be known statically per-Tool, no matter how many times that Tool is registered with
    // the platform (which may the case, for example, if Tools are expected to publish static
    // registration metadata as was the case in LTI 1.1)
    this(
        config,
        new AntRequestPathMatcher(
            StringUtils.isBlank(requestAntPathPattern)
                ? DEFAULT_REQUEST_ANT_PATH_PATTERN
                : requestAntPathPattern));

    // TODO do we need this bit?
    // setSuffix("/oauth2/oidc/lti/authorization");
  }

  public LtlOidcClientCallbackFilter(Config config, Matcher requestMatcher) {
    super(config);
    // TODO fall back something if no matcher specified? see comments in other constructor above
    this.requestMatcher = requestMatcher;
  }

  @Override
  protected boolean mustApply(final J2EContext context) {
    return requestMatcher.matches(context);
  }
}
