package com.jez.core.util;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by JEZ on 2017/5/28.
 */
@Component
@Lazy(false)
public class ApplicationContextAccessor implements ApplicationContextAware, DisposableBean {

  private static final String APPLICATION_CONTEXT_INJECTED = "The ApplicationContext had been injected already.";
  private static final String APPLICATION_CONTEXT_NOT_INJECTED = "The ApplicationContext haven't been injected yet.";

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    Validate.validState(ApplicationContextAccessor.applicationContext == null,
        APPLICATION_CONTEXT_INJECTED);
    ApplicationContextAccessor.applicationContext = applicationContext;
  }

  @Override
  public void destroy() throws Exception {
    ApplicationContextAccessor.applicationContext = null;
  }

  public static ApplicationContext getApplicationContext() {
    Validate.validState(ApplicationContextAccessor.applicationContext != null,
        APPLICATION_CONTEXT_NOT_INJECTED);
    return ApplicationContextAccessor.applicationContext;
  }

  public static <T> T getBean(Class<T> clazz) {
    return getBean(clazz, true);
  }

  public static <T> T getBean(Class<T> clazz, boolean raiseError) {
    try {
      return getApplicationContext().getBean(clazz);
    } catch (BeansException e) {
      if (raiseError) {
        throw e;
      }
      return null;
    }
  }
}
