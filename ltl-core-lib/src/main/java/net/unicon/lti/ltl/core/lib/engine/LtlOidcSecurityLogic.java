package net.unicon.lti.ltl.core.lib.engine;

import java.util.List;
import net.unicon.lti.ltl.core.lib.exception.LtlToolLookupException;
import net.unicon.lti.ltl.core.lib.exception.LtlToolLookupException.LtlToolLookupErrorCode;
import net.unicon.lti.ltl.core.lib.util.LtlUtils;
import org.pac4j.core.client.Client;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.Pac4jConstants;
import org.pac4j.core.engine.DefaultSecurityLogic;

public class LtlOidcSecurityLogic extends DefaultSecurityLogic<Object, J2EContext> {

  @Override
  protected void saveRequestedUrl(final J2EContext context, final List<Client> currentClients) {
    String targetLinkUri = context.getRequestParameter("target_link_uri");
    context.getSessionStore().set(context, Pac4jConstants.REQUESTED_URL, targetLinkUri);
  }

  @Override
  protected boolean startAuthentication(
      final J2EContext context, final List<Client> currentClients) {
    if (!(super.startAuthentication(context, currentClients))) {
      throw new LtlToolLookupException(LtlToolLookupErrorCode.TOOL_NOT_FOUND_DURING_AUTHENTICATION);
    }

    if (currentClients.size() > 1) {
      throw new LtlToolLookupException(
          LtlToolLookupErrorCode.TOO_MANY_TOOLS_FOUND_DURING_AUTHENTICATION);
    }

    LtlUtils.requireField("iss", context); // TODO verify that the Client expects this `iss`
    LtlUtils.requireField("login_hint", context); // Optional in OIDC, required in LTI
    LtlUtils.requireField("target_link_uri", context); // Optional in OIDC, required in LTI

    return true;
  }
}
