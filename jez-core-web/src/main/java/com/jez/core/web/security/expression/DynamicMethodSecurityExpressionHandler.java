package com.jez.core.web.security.expression;

import javax.annotation.Resource;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * Created by JEZ on 2017/5/28.
 */
public class DynamicMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

  @Resource
  private DynamicAuthorityService dynamicAuthorityService;

  @Override
  protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
      Authentication authentication, MethodInvocation invocation) {
    DynamicMethodSecurityExpressionRoot root = new DynamicMethodSecurityExpressionRoot(
        dynamicAuthorityService, authentication);
    root.setThis(invocation.getThis());
    root.setPermissionEvaluator(getPermissionEvaluator());
    root.setTrustResolver(getTrustResolver());
    root.setRoleHierarchy(getRoleHierarchy());
    root.setDefaultRolePrefix(getDefaultRolePrefix());
    return root;
  }
}
