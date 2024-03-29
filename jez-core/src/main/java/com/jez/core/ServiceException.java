package com.jez.core;

/**
 * Created by JEZ on 2017/5/10.
 */
public class ServiceException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = -8164946313561208902L;

  public ServiceException() {
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }

  public ServiceException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
