package com.jez.modules.admin.system.security.userdetails;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.User;
import com.jez.modules.admin.system.service.GroupService;
import com.jez.modules.admin.system.service.ResourceService;
import com.jez.modules.admin.system.service.UserService;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by JEZ on 2017/5/21.
 */
public class UserDetailsServiceWrapper implements UserDetailsService {

  @Resource
  private UserService userService;

  @Resource
  private ResourceService resourceService;

  @Resource
  private GroupService groupService;

  private String rolePrefix = "ROLE_";

  public UserDetailsServiceWrapper() {
  }

  public UserDetailsServiceWrapper(String rolePrefix) {
    this.rolePrefix = rolePrefix != null ? rolePrefix : "";
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      User user = userService.getByUsername(username);
      return new UserDetailsWrapper(user, getAuthoritySet(user.getId()));
    } catch (ServiceException e) {
      throw new UsernameNotFoundException(null, e);
    }
  }

  public Set<SimpleGrantedAuthority> getAuthoritySet(Long userId) throws ServiceException {
    Set<String> authorities = resourceService.findPermissionByUserId(userId);
    Set<String> groupCodes = groupService.findCodeByUserId(userId);
    if (rolePrefix != null) {
      authorities.addAll(groupCodes.stream().map(c -> rolePrefix + c).collect(Collectors.toSet()));
    } else {
      authorities.addAll(groupCodes);
    }
    return authorities.stream().map(a -> new SimpleGrantedAuthority(a)).collect(Collectors.toSet());
  }

}
