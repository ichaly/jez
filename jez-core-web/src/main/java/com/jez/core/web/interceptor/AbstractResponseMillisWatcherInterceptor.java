package com.jez.core.web.interceptor;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by JEZ on 2017/5/17.
 */
public abstract class AbstractResponseMillisWatcherInterceptor extends HandlerInterceptorAdapter {

  private ThreadLocal<StopWatch> stopWatchThreadLocal = new ThreadLocal<>();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatchThreadLocal.set(stopWatch);
    stopWatch.start();
    return super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    super.postHandle(request, response, handler, modelAndView);
    StopWatch stopWatch = stopWatchThreadLocal.get();
    if (stopWatch != null) {
      stopWatch.stop();
    }
  }

  protected abstract void afterCompletion(Date startTime, Long responseMillis,
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex);

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    super.afterCompletion(request, response, handler, ex);
    Date startTime;
    Long responseMillis = null;
    StopWatch stopWatch = stopWatchThreadLocal.get();
    if (stopWatch != null) {
      startTime = new Date(stopWatch.getStartTime());
      responseMillis = stopWatch.getTime();
      stopWatchThreadLocal.set(null);
    } else {
      startTime = new Date();
    }
    afterCompletion(startTime, responseMillis, request, response, handler, ex);
  }

}
