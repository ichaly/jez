package com.jez.modules.admin.system.security.userdetails;

import com.jez.core.persistence.enums.EnableStatus;
import com.jez.modules.admin.system.entity.User;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * Created by JEZ on 2017/5/21.
 */
public class UserDetailsWrapper implements UserDetails {

  /**
   *
   */
  private static final long serialVersionUID = -8564140049213257857L;

  private User user;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsWrapper(User user, Collection<? extends GrantedAuthority> authorities) {
    Assert.notNull(user, "ApplicationAccount must not be null!");
    this.user = user;
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return isEnabled();
  }

  @Override
  public boolean isAccountNonLocked() {
    return isEnabled();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isEnabled();
  }

  @Override
  public boolean isEnabled() {
    return user.getStatus() == EnableStatus.ENABLED.getValue();
  }

  public User getUser() {
    return user;
  }
}
