package com.jez.modules.admin.system.security.authentication;

import com.jez.modules.admin.system.service.LoginLogService;
import javax.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by JEZ on 2017/7/28.
 */
@Component
public class SessionKickOutManager implements ApplicationContextAware {

  private SessionRegistry sessionRegistry;

  @Resource
  private LoginLogService loginLogService;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    try {
      sessionRegistry = applicationContext.getBean(SessionRegistry.class);
    } catch (BeansException e) {
    }
  }

  public void kickOut(Long userId) throws Exception {
    Assert.notNull(sessionRegistry, "SessionRegistry not found.");
    loginLogService.findUnLogoutByUserId(userId).stream()
        .map(l -> sessionRegistry.getSessionInformation(l.getSessionId())).filter(s -> s != null)
        .forEach(s -> s.expireNow());
  }

}
