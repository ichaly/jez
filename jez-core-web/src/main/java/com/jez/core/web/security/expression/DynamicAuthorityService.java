package com.jez.core.web.security.expression;

import java.util.Set;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.Authentication;

/**
 * Created by JEZ on 2017/5/28.
 */
public interface DynamicAuthorityService {

  Set<String> getAuthoritySet(Authentication authentication, RoleHierarchy roleHierarchy)
      throws Exception;

}
