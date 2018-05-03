package com.jez.modules.admin.system.security.util;

import com.jez.modules.admin.system.entity.User;
import com.jez.modules.admin.system.security.userdetails.UserDetailsWrapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by JEZ on 2017/5/30.
 */
public class SecurityUtils {

  private SecurityUtils() {
  }

  public static Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public static boolean isAuthenticated(Authentication authentication) {
    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
        && authentication.isAuthenticated();
  }

  public static boolean isAuthenticated() {
    return isAuthenticated(getAuthentication());
  }

  public static Object getPrinciple(Authentication authentication) {
    return authentication != null ? authentication.getPrincipal() : null;
  }

  public static Object getPrinciple() {
    return getPrinciple(getAuthentication());
  }

  public static <T> T getPrinciple(Authentication authentication, Class<T> clazz) {
    return getPrinciple(getPrinciple(authentication), clazz);
  }

  @SuppressWarnings("unchecked")
  public static <T> T getPrinciple(Object principle, Class<T> clazz) {
    return principle != null ? (T) principle : null;
  }

  public static <T> T getPrinciple(Class<T> clazz) {
    return getPrinciple(getPrinciple(), clazz);
  }

  public static User getUser(Authentication authentication) {
    return getUser(getPrinciple(authentication));
  }

  public static User getUser(Object principle) {
    UserDetailsWrapper userDetailsWrapper = getPrinciple(principle, UserDetailsWrapper.class);
    return userDetailsWrapper != null ? userDetailsWrapper.getUser() : null;
  }

  public static User getUser() {
    return getUser(getPrinciple());
  }

  public static Long getUserId(Authentication authentication) {
    return getUserId(getUser(authentication));
  }

  public static Long getUserId(User user) {
    return user != null ? user.getId() : null;
  }

  public static Long getUserId() {
    return getUserId(getUser());
  }

}
