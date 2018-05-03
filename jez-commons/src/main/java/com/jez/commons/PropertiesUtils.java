package com.jez.commons;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Properties;

/**
 * Created by JEZ on 2017/5/8.
 */
public class PropertiesUtils {

  private PropertiesUtils() {
  }

  public static String getString(Properties properties, String key, String defaultValue,
      boolean isEmptyValueAccepted) {
    if (properties == null || StringUtils.isEmpty(key)) {
      return defaultValue;
    }
    String value = properties.getProperty(key);
    if (value == null) {
      return defaultValue;
    }
    if (StringUtils.isEmpty(value) && !isEmptyValueAccepted) {
      return defaultValue;
    }
    return value;
  }

  public static String getString(Properties properties, String key, String defaultValue) {
    return getString(properties, key, defaultValue, true);
  }

  public static String getString(Properties properties, String key) {
    return getString(properties, key, null);
  }

  public static String getString(Properties properties, String key, boolean isEmptyValueAccepted) {
    return getString(properties, key, null, isEmptyValueAccepted);
  }

  public static Boolean getBoolean(Properties properties, String key, Boolean defaultValue) {
    String value = getString(properties, key);
    try {
      return StringUtils.isNotEmpty(value) ? Boolean.parseBoolean(value) : defaultValue;
    } catch (Exception e) {
      return defaultValue;
    }
  }

  public static Boolean getBoolean(Properties properties, String key) {
    return getBoolean(properties, key, Boolean.FALSE);
  }

  public static Byte getByte(Properties properties, String key, Byte defaultValue) {
    String value = getString(properties, key);
    try {
      return StringUtils.isNotEmpty(value) ? Byte.parseByte(value) : defaultValue;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public static Byte getByte(Properties properties, String key) {
    return getByte(properties, key, BigDecimal.ZERO.byteValue());
  }

  public static Short getShort(Properties properties, String key, Short defaultValue) {
    String value = getString(properties, key);
    try {
      return StringUtils.isNotEmpty(value) ? Short.parseShort(value) : defaultValue;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public static Short getShort(Properties properties, String key) {
    return getShort(properties, key, BigDecimal.ZERO.shortValue());
  }

  public static Integer getInteger(Properties properties, String key, Integer defaultValue) {
    String value = getString(properties, key);
    try {
      return StringUtils.isNotEmpty(value) ? Integer.parseInt(value) : defaultValue;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public static Integer getInteger(Properties properties, String key) {
    return getInteger(properties, key, BigDecimal.ZERO.intValue());
  }

  public static Long getLong(Properties properties, String key, Long defaultValue) {
    String value = getString(properties, key);
    try {
      return StringUtils.isNotEmpty(value) ? Long.parseLong(value) : defaultValue;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public static Long getLong(Properties properties, String key) {
    return getLong(properties, key, BigDecimal.ZERO.longValue());
  }

  public static Float getFloat(Properties properties, String key, Float defaultValue) {
    String value = getString(properties, key);
    try {
      return StringUtils.isNotEmpty(value) ? Float.parseFloat(value) : defaultValue;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public static Float getFloat(Properties properties, String key) {
    return getFloat(properties, key, BigDecimal.ZERO.floatValue());
  }

  public static Double getDouble(Properties properties, String key, Double defaultValue) {
    String value = getString(properties, key);
    try {
      return StringUtils.isNotEmpty(value) ? Double.parseDouble(value) : defaultValue;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public static Double getDouble(Properties properties, String key) {
    return getDouble(properties, key, BigDecimal.ZERO.doubleValue());
  }

}
