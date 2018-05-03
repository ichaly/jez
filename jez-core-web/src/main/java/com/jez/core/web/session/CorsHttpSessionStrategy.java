package com.jez.core.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.session.Session;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.MultiHttpSessionStrategy;

/**
 * Created by JEZ on 2017/6/22.
 */
public class CorsHttpSessionStrategy implements MultiHttpSessionStrategy {

  private HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();
  private CookieHttpSessionStrategy cookieHttpSessionStrategy = new CookieHttpSessionStrategy();

  @Override
  public String getRequestedSessionId(HttpServletRequest request) {
    String sessionId = headerHttpSessionStrategy.getRequestedSessionId(request);
    if (StringUtils.isEmpty(sessionId)) {
      sessionId = cookieHttpSessionStrategy.getRequestedSessionId(request);
    }
    return sessionId;
  }

  @Override
  public void onNewSession(Session session, HttpServletRequest request,
      HttpServletResponse response) {
    headerHttpSessionStrategy.onNewSession(session, request, response);
    cookieHttpSessionStrategy.onNewSession(session, request, response);
  }

  @Override
  public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
    headerHttpSessionStrategy.onInvalidateSession(request, response);
    cookieHttpSessionStrategy.onInvalidateSession(request, response);
  }

  @Override
  public HttpServletRequest wrapRequest(HttpServletRequest request, HttpServletResponse response) {
    return cookieHttpSessionStrategy.wrapRequest(request, response);
  }

  @Override
  public HttpServletResponse wrapResponse(HttpServletRequest request,
      HttpServletResponse response) {
    return cookieHttpSessionStrategy.wrapResponse(request, response);
  }
}
