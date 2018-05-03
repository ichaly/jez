package com.jez.modules.admin.system.security.authentication;

import com.jez.core.ServiceException;
import com.jez.core.web.security.authentication.HttpStatusLogoutSuccessHandler;
import com.jez.modules.admin.system.security.util.SecurityUtils;
import com.jez.modules.admin.system.service.LoginLogService;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

/**
 * Created by JEZ on 2017/7/17.
 */
public class LogLogoutSuccessHandler extends HttpStatusLogoutSuccessHandler {

  private static Logger logger = LoggerFactory.getLogger(LogLogoutSuccessHandler.class);

  private static final int DEFAULT_NUMBER_OF_THREADS = 10;

  @Resource
  private LoginLogService loginLogService;

  private ExecutorService executor;

  public LogLogoutSuccessHandler(HttpStatus httpStatusToReturn, int numberOfThreads) {
    super(httpStatusToReturn);
    this.executor = Executors.newFixedThreadPool(numberOfThreads);
  }

  public LogLogoutSuccessHandler(int numberOfThreads) {
    super();
    this.executor = Executors.newFixedThreadPool(numberOfThreads);
  }

  public LogLogoutSuccessHandler(HttpStatus httpStatusToReturn) {
    this(httpStatusToReturn, DEFAULT_NUMBER_OF_THREADS);
  }

  public LogLogoutSuccessHandler() {
    this(DEFAULT_NUMBER_OF_THREADS);
  }

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    super.onLogoutSuccess(request, response, authentication);
    Long userId = SecurityUtils.getUserId(authentication);
    if (userId != null) {
      Date logoutTime = new Date();
      executor.execute(() -> {
        try {
          loginLogService.logLogout(userId, logoutTime);
        } catch (ServiceException e) {
          logger.error("Failed to log logout.", e);
        }
      });
    }
  }

}
