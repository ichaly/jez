package com.jez.modules.admin.system.security.expression;

import com.jez.core.web.security.expression.DynamicAuthorityService;
import com.jez.modules.admin.system.security.userdetails.UserDetailsWrapper;
import com.jez.modules.admin.system.service.GroupService;
import com.jez.modules.admin.system.service.ResourceService;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Created by JEZ on 2017/5/28.
 */
public class CacheDynamicAuthorityService implements DynamicAuthorityService {

  @Resource
  private ResourceService resourceService;

  @Resource
  private GroupService groupService;

  private Cache cache;

  private String rolePrefix = "ROLE_";

  public CacheDynamicAuthorityService(Cache cache) {
    this.cache = cache != null ? cache
        : new ConcurrentMapCache(CacheDynamicAuthorityService.class.getName());
  }

  public CacheDynamicAuthorityService(Cache cache, String rolePrefix) {
    this.cache = cache != null ? cache
        : new ConcurrentMapCache(CacheDynamicAuthorityService.class.getName());
    this.rolePrefix = rolePrefix != null ? rolePrefix : "";
  }

  @SuppressWarnings("unchecked")
  @Override
  public Set<String> getAuthoritySet(Authentication authentication, RoleHierarchy roleHierarchy)
      throws Exception {
    Long userId = ((UserDetailsWrapper) authentication.getPrincipal()).getUser().getId();
    ValueWrapper valueWrapper = cache.get(userId.toString());
    if (valueWrapper == null || valueWrapper.get() == null) {

      Set<String> authorities = resourceService.findPermissionByUserId(userId);
      Set<String> groupCodes = groupService.findCodeByUserId(userId);
      if (rolePrefix != null) {
        authorities
            .addAll(groupCodes.stream().map(c -> rolePrefix + c).collect(Collectors.toSet()));
      } else {
        authorities.addAll(groupCodes);
      }
      if (roleHierarchy != null) {
        authorities = AuthorityUtils.authorityListToSet(roleHierarchy
            .getReachableGrantedAuthorities(
                authorities.stream().map(a -> new SimpleGrantedAuthority(a))
                    .collect(Collectors.toList())));
      }
      cache.put(userId.toString(), authorities);
      return authorities;
    }
    return (Set<String>) valueWrapper.get();
  }

}
