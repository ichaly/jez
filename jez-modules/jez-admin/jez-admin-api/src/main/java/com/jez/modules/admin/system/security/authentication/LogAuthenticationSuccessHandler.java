package com.jez.modules.admin.system.security.authentication;

import com.jez.core.ServiceException;
import com.jez.core.web.security.authentication.HttpStatusAuthenticationSuccessHandler;
import com.jez.core.web.util.ServletUtils;
import com.jez.modules.admin.system.security.util.SecurityUtils;
import com.jez.modules.admin.system.service.LoginLogService;
import eu.bitwalker.useragentutils.UserAgent;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
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
 * Created by JEZ on 2017/6/13.
 */
public class LogAuthenticationSuccessHandler extends HttpStatusAuthenticationSuccessHandler {

  private static Logger logger = LoggerFactory.getLogger(LogAuthenticationSuccessHandler.class);

  private static final int DEFAULT_NUMBER_OF_THREADS = 10;

  @Resource
  private LoginLogService loginLogService;

  private ExecutorService executor;

  public LogAuthenticationSuccessHandler(HttpStatus httpStatusToReturn, int numberOfThreads) {
    super(httpStatusToReturn);
    this.executor = Executors.newFixedThreadPool(numberOfThreads);
  }

  public LogAuthenticationSuccessHandler(int numberOfThreads) {
    super();
    this.executor = Executors.newFixedThreadPool(numberOfThreads);
  }

  public LogAuthenticationSuccessHandler(HttpStatus httpStatusToReturn) {
    this(httpStatusToReturn, DEFAULT_NUMBER_OF_THREADS);
  }

  public LogAuthenticationSuccessHandler() {
    this(DEFAULT_NUMBER_OF_THREADS);
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    super.onAuthenticationSuccess(request, response, authentication);
    Long userId = SecurityUtils.getUserId();
    String sessionId = request.getSession().getId();
    Date loginTime = new Date();
    Map<String, String> map = new HashMap<>();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      map.put(headerName, request.getHeader(headerName));
    }
    String clientIp = ServletUtils.getRemoteAddress(request);
    UserAgent userAgent = ServletUtils.getUserAgent(request);
    String clientDevice = userAgent.getOperatingSystem().getDeviceType().getName();
    String clientOs = userAgent.getOperatingSystem().getName();
    String clientBrowser = userAgent.getBrowser().getName();
    String clientBrowserVersion = userAgent.getBrowserVersion().getVersion();
    executor.execute(() -> {
      try {
        loginLogService
            .logLogin(userId, sessionId, loginTime, clientIp, clientDevice, clientOs, clientBrowser,
                clientBrowserVersion);
      } catch (ServiceException e) {
        logger.error("Failed to log login.", e);
      }
    });
  }
}
