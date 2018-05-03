package com.jez.modules.admin.system.listener;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.service.LoginLogService;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JEZ on 2017/7/25.
 */
public class LogHttpSessionListener implements HttpSessionListener {

  private static Logger logger = LoggerFactory.getLogger(LogHttpSessionListener.class);

  @Resource
  private LoginLogService loginLogService;

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    if (logger.isDebugEnabled()) {
      logger.debug("Session created {}", se.getSession().getId());
    }
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    if (logger.isDebugEnabled()) {
      logger.debug("Session destroyed {}", se.getSession().getId());
    }
    try {
      loginLogService.logLogout(se.getSession().getId(), new Date());
    } catch (ServiceException e) {
      logger
          .error("Failed to log logout cause by session destroyed {}", se.getSession().getId(), e);
    }
  }

}
