package com.jez.core.web.security.authentication;

import javax.annotation.Resource;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;

/**
 * Created by JEZ on 2017/5/21.
 */
public class RetryLimitAuthenticationProvider extends DaoAuthenticationProvider {

  @Resource
  private RetryCountService retryCountService;

  @Resource
  private LockUserService lockUserService;

  private int maxAttempts;

  public RetryLimitAuthenticationProvider(Integer maxAttempts) {
    Assert.isTrue(maxAttempts > 0, "Max attempts must be positive");
    this.maxAttempts = maxAttempts;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username =
        (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
    int retryCount = retryCountService.getByUsername(username);
    if (retryCount >= maxAttempts) {
      if (lockUserService.lockByUsername(username)) {
        retryCountService.deleteByUsername(username);
      }
      throw new LockedException(String.valueOf(retryCount));
    }
    try {
      Authentication result = super.authenticate(authentication);
      retryCountService.deleteByUsername(username);
      return result;
    } catch (AuthenticationException e) {
      retryCountService.increaseByUsername(username);
      throw e;
    }
  }

}
