package com.jez.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * Created by JEZ on 2017/5/8.
 */
public class LoggerUtils {

  private static Logger logger = LoggerFactory.getLogger(LoggerUtils.class);

  private LoggerUtils() {
  }

  public static void trace(String msg) {
    if (logger.isTraceEnabled()) {
      logger.trace(msg);
    }
  }

  public static void trace(String format, Object arg) {
    if (logger.isTraceEnabled()) {
      logger.trace(format, arg);
    }
  }

  public static void trace(String format, Object arg1, Object arg2) {
    if (logger.isTraceEnabled()) {
      logger.trace(format, arg1, arg2);
    }
  }

  public static void trace(String format, Object... arguments) {
    if (logger.isTraceEnabled()) {
      logger.trace(format, arguments);
    }
  }

  public static void trace(String msg, Throwable t) {
    if (logger.isTraceEnabled()) {
      logger.trace(msg, t);
    }
  }

  public static void trace(Marker marker, String msg) {
    if (logger.isTraceEnabled()) {
      logger.trace(marker, msg);
    }
  }

  public static void trace(Marker marker, String format, Object arg) {
    if (logger.isTraceEnabled()) {
      logger.trace(marker, format, arg);
    }
  }

  public static void trace(Marker marker, String format, Object arg1, Object arg2) {
    if (logger.isTraceEnabled()) {
      logger.trace(marker, format, arg1, arg2);
    }
  }

  public static void trace(Marker marker, String format, Object... argArray) {
    if (logger.isTraceEnabled()) {
      logger.trace(marker, format, argArray);
    }
  }

  public static void trace(Marker marker, String msg, Throwable t) {
    if (logger.isTraceEnabled()) {
      logger.trace(marker, msg, t);
    }
  }

  public static void debug(String msg) {
    if (logger.isDebugEnabled()) {
      logger.debug(msg);
    }
  }

  public static void debug(String format, Object arg) {
    if (logger.isDebugEnabled()) {
      logger.debug(format, arg);
    }
  }

  public static void debug(String format, Object arg1, Object arg2) {
    if (logger.isDebugEnabled()) {
      logger.debug(format, arg1, arg2);
    }
  }

  public static void debug(String format, Object... arguments) {
    if (logger.isDebugEnabled()) {
      logger.debug(format, arguments);
    }
  }

  public static void debug(String msg, Throwable t) {
    if (logger.isDebugEnabled()) {
      logger.debug(msg, t);
    }
  }

  public static void debug(Marker marker, String msg) {
    if (logger.isDebugEnabled()) {
      logger.debug(marker, msg);
    }
  }

  public static void debug(Marker marker, String format, Object arg) {
    if (logger.isDebugEnabled()) {
      logger.debug(marker, format, arg);
    }
  }

  public static void debug(Marker marker, String format, Object arg1, Object arg2) {
    if (logger.isDebugEnabled()) {
      logger.debug(marker, format, arg1, arg2);
    }
  }

  public static void debug(Marker marker, String format, Object... arguments) {
    if (logger.isDebugEnabled()) {
      logger.debug(marker, format, arguments);
    }
  }

  public static void debug(Marker marker, String msg, Throwable t) {
    if (logger.isDebugEnabled()) {
      logger.debug(marker, msg, t);
    }
  }

  public static void info(String msg) {
    if (logger.isInfoEnabled()) {
      logger.info(msg);
    }
  }

  public static void info(String format, Object arg) {
    if (logger.isInfoEnabled()) {
      logger.info(format, arg);
    }
  }

  public static void info(String format, Object arg1, Object arg2) {
    if (logger.isInfoEnabled()) {
      logger.info(format, arg1, arg2);
    }
  }

  public static void info(String format, Object... arguments) {
    if (logger.isInfoEnabled()) {
      logger.info(format, arguments);
    }
  }

  public static void info(String msg, Throwable t) {
    if (logger.isInfoEnabled()) {
      logger.info(msg, t);
    }
  }

  public static void info(Marker marker, String msg) {
    if (logger.isInfoEnabled()) {
      logger.info(marker, msg);
    }
  }

  public static void info(Marker marker, String format, Object arg) {
    if (logger.isInfoEnabled()) {
      logger.info(marker, format, arg);
    }
  }

  public static void info(Marker marker, String format, Object arg1, Object arg2) {
    if (logger.isInfoEnabled()) {
      logger.info(marker, format, arg1, arg2);
    }
  }

  public static void info(Marker marker, String format, Object... arguments) {
    if (logger.isInfoEnabled()) {
      logger.info(marker, format, arguments);
    }
  }

  public static void info(Marker marker, String msg, Throwable t) {
    if (logger.isInfoEnabled()) {
      logger.info(marker, msg, t);
    }
  }

  public static void warn(String msg) {
    if (logger.isWarnEnabled()) {
      logger.warn(msg);
    }
  }

  public static void warn(String format, Object arg) {
    if (logger.isWarnEnabled()) {
      logger.warn(format, arg);
    }
  }

  public static void warn(String format, Object... arguments) {
    if (logger.isWarnEnabled()) {
      logger.warn(format, arguments);
    }
  }

  public static void warn(String format, Object arg1, Object arg2) {
    if (logger.isWarnEnabled()) {
      logger.warn(format, arg1, arg2);
    }
  }

  public static void warn(String msg, Throwable t) {
    if (logger.isWarnEnabled()) {
      logger.warn(msg, t);
    }
  }

  public static void warn(Marker marker, String msg) {
    if (logger.isWarnEnabled()) {
      logger.warn(marker, msg);
    }
  }

  public static void warn(Marker marker, String format, Object arg) {
    if (logger.isWarnEnabled()) {
      logger.warn(marker, format, arg);
    }
  }

  public static void warn(Marker marker, String format, Object arg1, Object arg2) {
    if (logger.isWarnEnabled()) {
      logger.warn(marker, format, arg1, arg2);
    }
  }

  public static void warn(Marker marker, String format, Object... arguments) {
    if (logger.isWarnEnabled()) {
      logger.warn(marker, format, arguments);
    }
  }

  public static void warn(Marker marker, String msg, Throwable t) {
    if (logger.isWarnEnabled()) {
      logger.warn(marker, msg, t);
    }
  }

  public static void error(String msg) {
    if (logger.isErrorEnabled()) {
      logger.error(msg);
    }
  }

  public static void error(String format, Object arg) {
    if (logger.isErrorEnabled()) {
      logger.error(format, arg);
    }
  }

  public static void error(String format, Object arg1, Object arg2) {
    if (logger.isErrorEnabled()) {
      logger.error(format, arg1, arg2);
    }
  }

  public static void error(String format, Object... arguments) {
    if (logger.isErrorEnabled()) {
      logger.error(format, arguments);
    }
  }

  public static void error(String msg, Throwable t) {
    if (logger.isErrorEnabled()) {
      logger.error(msg, t);
    }
  }

  public static void error(Marker marker, String msg) {
    if (logger.isErrorEnabled()) {
      logger.error(marker, msg);
    }
  }

  public static void error(Marker marker, String format, Object arg) {
    if (logger.isErrorEnabled()) {
      logger.error(marker, format, arg);
    }
  }

  public static void error(Marker marker, String format, Object arg1, Object arg2) {
    if (logger.isErrorEnabled()) {
      logger.error(marker, format, arg1, arg2);
    }
  }

  public static void error(Marker marker, String format, Object... arguments) {
    if (logger.isErrorEnabled()) {
      logger.error(marker, format, arguments);
    }
  }

  public static void error(Marker marker, String msg, Throwable t) {
    if (logger.isErrorEnabled()) {
      logger.error(marker, msg, t);
    }
  }
}
