package com.jez.modules.admin.system.interceptor;

import com.jez.commons.JsonUtils;
import com.jez.core.ServiceException;
import com.jez.core.web.interceptor.AbstractResponseMillisWatcherInterceptor;
import com.jez.core.web.util.ServletUtils;
import com.jez.modules.admin.system.security.util.SecurityUtils;
import com.jez.modules.admin.system.service.OperationLogService;
import eu.bitwalker.useragentutils.UserAgent;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

/**
 * Created by JEZ on 2017/7/15.
 */
public class OperationLogInterceptor extends AbstractResponseMillisWatcherInterceptor {

  private static Logger logger = LoggerFactory.getLogger(OperationLogInterceptor.class);

  private static final int DEFAULT_MAX_PAYLOAD_LENGTH = 50;
  private static final int DEFAULT_NUMBER_OF_THREADS = 10;

  private static final long RESPONSE_MILLIS_TO_WARN = 5000L;
  private static final String MSG_REQUEST_LOG = "Response {} in {}ms for [{}] {} {}";

  private int maxPayloadLength = DEFAULT_MAX_PAYLOAD_LENGTH;

  private ExecutorService executor;

  @Resource
  private OperationLogService operationLogService;

  public OperationLogInterceptor() {
    this(DEFAULT_NUMBER_OF_THREADS);
  }

  public OperationLogInterceptor(int numberOfThreads) {
    this.executor = Executors.newFixedThreadPool(numberOfThreads);
  }

  @Override
  protected void afterCompletion(Date startTime, Long responseMillis, HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex) {
    Long userId = SecurityUtils.getUserId();
    int responseStatus = response.getStatus();
    String serverPath = ServletUtils.getServerPath(request);
    String uri = request.getRequestURI();
    String method = ServletUtils.getMethod(request);
    String parameters = ServletUtils.getParameters(request);
    String remoteAddress = ServletUtils.getRemoteAddress(request);
    if (responseMillis > RESPONSE_MILLIS_TO_WARN) {
      logger.warn(MSG_REQUEST_LOG, responseStatus, responseMillis, remoteAddress, method,
          ServletUtils.concatUrlAndParameters(serverPath + uri, parameters));
    } else {
      logger.info(MSG_REQUEST_LOG, responseStatus, responseMillis, remoteAddress, method,
          ServletUtils.concatUrlAndParameters(serverPath + uri, parameters));
    }
    String matchingPattern = (String) request
        .getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
    Map<String, String> map = new HashMap<>();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      map.put(headerName, request.getHeader(headerName));
    }
    String headers = JsonUtils.toJson(map);
    ContentCachingRequestWrapper wrapper =
        WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
    String requestBody = null;
    if (wrapper != null) {
      byte[] buf = wrapper.getContentAsByteArray();
      if (buf.length > 0) {
        int length = Math.min(buf.length, getMaxPayloadLength());
        try {
          requestBody = new String(buf, 0, length, wrapper.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
        }
      }
    }
    final String payload = requestBody != null ? requestBody : null;
    UserAgent userAgent = ServletUtils.getUserAgent(request);
    String clientDevice = userAgent.getOperatingSystem().getDeviceType().getName();
    String clientOs = userAgent.getOperatingSystem().getName();
    String clientBrowser = userAgent.getBrowser().getName();
    String clientBrowserVersion = userAgent.getBrowserVersion().getVersion();
    executor.execute(() -> {
      try {
        operationLogService
            .logOperation(userId, startTime, matchingPattern, method, serverPath, uri, headers,
                parameters, payload, responseStatus, responseMillis, remoteAddress, clientDevice,
                clientOs, clientBrowser, clientBrowserVersion);
      } catch (ServiceException e) {
        logger.error("Failed to log operation.", e);
      }
    });
  }

  public int getMaxPayloadLength() {
    return maxPayloadLength;
  }

  public void setMaxPayloadLength(int maxPayloadLength) {
    this.maxPayloadLength = maxPayloadLength;
  }

}
