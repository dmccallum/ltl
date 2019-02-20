package net.unicon.lti.ltl.springsecurity.lib.util;

import java.util.function.Function;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.matching.Matcher;
import org.springframework.util.AntPathMatcher;

public class AntRequestPathMatcher implements Matcher {

  private final String antPathPattern;
  private final AntPathMatcher antPathMatcher;

  public AntRequestPathMatcher(String antPathPattern) {
    this(antPathPattern, null);
  }

  public AntRequestPathMatcher(
      String antPathPattern, Function<AntPathMatcher, AntPathMatcher> antPathMatcherMutator) {
    this.antPathPattern = antPathPattern;
    this.antPathMatcher =
        ((antPathMatcherMutator == null)
                ? Function.<AntPathMatcher>identity()
                : antPathMatcherMutator)
            .apply(new AntPathMatcher("/"));
  }

  @Override
  public boolean matches(WebContext context) {
    return antPathMatcher.match(antPathPattern, context.getPath());
  }
}
