package com.jez.core.web.security.expression;

import javax.annotation.Resource;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * Created by JEZ on 2017/5/28.
 */
public class DynamicWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {

  private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
  private String defaultRolePrefix = "ROLE_";

  @Resource
  private DynamicAuthorityService dynamicAuthorityService;

  @Override
  protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
      FilterInvocation fi) {
    DynamicWebSecurityExpressionRoot root = new DynamicWebSecurityExpressionRoot(
        dynamicAuthorityService, authentication, fi);
    root.setPermissionEvaluator(getPermissionEvaluator());
    root.setTrustResolver(trustResolver);
    root.setRoleHierarchy(getRoleHierarchy());
    root.setDefaultRolePrefix(this.defaultRolePrefix);
    return root;
  }

}
