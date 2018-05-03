package com.jez.modules.admin.system.security.userdetails;

import com.jez.core.ServiceException;
import com.jez.core.web.security.authentication.LockUserService;
import com.jez.modules.admin.system.service.UserService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JEZ on 2017/5/22.
 */
public class LockUserServiceWrapper implements LockUserService {

  private static Logger logger = LoggerFactory.getLogger(LockUserServiceWrapper.class);

  @Resource
  private UserService userService;

  public LockUserServiceWrapper() {
  }

  @Override
  public boolean lockByUsername(String username) {
    try {
      userService.disableByUsername(username);
      return true;
    } catch (ServiceException e) {
      logger.error("Failed to lock user.", e);
      return false;
    }
  }
}
