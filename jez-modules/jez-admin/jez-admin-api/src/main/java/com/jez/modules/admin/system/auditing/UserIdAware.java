package com.jez.modules.admin.system.auditing;

import com.jez.modules.admin.system.security.util.SecurityUtils;
import org.springframework.data.domain.AuditorAware;

/**
 * Created by JEZ on 2017/5/17.
 */
public class UserIdAware implements AuditorAware<Long> {

  @Override
  public Long getCurrentAuditor() {
    return SecurityUtils.getUserId();
  }
}
