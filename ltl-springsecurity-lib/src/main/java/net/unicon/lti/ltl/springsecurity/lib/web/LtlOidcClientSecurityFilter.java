package net.unicon.lti.ltl.springsecurity.lib.web;

import org.pac4j.core.config.Config;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.engine.SecurityLogic;
import org.pac4j.springframework.security.web.SecurityFilter;

public class LtlOidcClientSecurityFilter extends SecurityFilter {

  // TODO rethink whether we need this class at all since we can inject the security logic
  //   via a setter... there's nothing actually spring-specific here and we could do what
  //   we need to do via an autoconfig module instead, or possibly just stuffing it into
  //   the sample app
  public LtlOidcClientSecurityFilter(
      Config config, SecurityLogic<Object, J2EContext> securityLogic) {
    // TODO historically we also passed a list of client IDs - this is obviously a no-go since we
    //  won't know that list at startup time, but am unsure what the consequences are of not
    //  preconfiguring it into the filter
    super(config);
    setSecurityLogic(securityLogic);
  }
}
