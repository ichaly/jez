package com.jez.core.web.util;

import com.jez.core.persistence.enums.RequestMethod;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by JEZ on 2017/5/17.
 */
public class ServletUtils {

  private static final String UNKNOWN = "unknown";
  private static final String HEADER_X_REAL_IP = "X-Real-IP";
  private static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
  private static final String SEPARATOR_REVERSE_PROXY_IP = ",";
  private static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
  private static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
  private static final String HEADER_USER_AGENT = "user-agent";
  private static final String HEADER_X_REQUESTED_WITH = "x-requested-with";

  private static final int HTTP_PORT = 80;
  private static final String FORMATTER_SERVER_PATH_WITH_HTTP_PORT = "%s://%s";
  private static final String FORMATTER_SERVER_PATH = "%s://%s:%s";
  private static final String SEPARATOR_PARAMETER = "&";
  private static final String FORMATTER_PARAMETER = "%s=%s";
  private static final String SEPARATOR_URL_AND_PARAMETERS = "?";
  private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
  private static final String LOCALHOST_REMOTE_ADDRESS = "0:0:0:0:0:0:0:1";

  private ServletUtils() {
  }

  public static String getRemoteAddress(HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    String remoteAddress = request.getHeader(HEADER_X_REAL_IP);
    if (StringUtils.isNotBlank(remoteAddress) && !UNKNOWN.equalsIgnoreCase(remoteAddress)) {
      return remoteAddress;
    }
    remoteAddress = request.getHeader(HEADER_X_FORWARDED_FOR);
    if (StringUtils.isNotBlank(remoteAddress) && !UNKNOWN.equalsIgnoreCase(remoteAddress)) {
      int index = remoteAddress.indexOf(SEPARATOR_REVERSE_PROXY_IP);
      return index != -1 ? remoteAddress.substring(0, index) : remoteAddress;
    }
    remoteAddress = request.getHeader(HEADER_PROXY_CLIENT_IP);
    if (StringUtils.isNotBlank(remoteAddress) && !UNKNOWN.equalsIgnoreCase(remoteAddress)) {
      return remoteAddress;
    }
    remoteAddress = request.getHeader(HEADER_WL_PROXY_CLIENT_IP);
    if (StringUtils.isNotBlank(remoteAddress) && !UNKNOWN.equalsIgnoreCase(remoteAddress)) {
      return remoteAddress;
    }
    remoteAddress = request.getRemoteAddr();
    try {
      return StringUtils.equals(LOCALHOST_REMOTE_ADDRESS, remoteAddress) ? InetAddress
          .getLocalHost().getHostAddress() : remoteAddress;
    } catch (UnknownHostException e) {
      return remoteAddress;
    }
  }

  public static String getUserAgentString(HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    return request.getHeader(HEADER_USER_AGENT);
  }

  public static UserAgent getUserAgent(HttpServletRequest request) {
    String userAgentString = getUserAgentString(request);
    return StringUtils.isNotEmpty(userAgentString) ? UserAgent.parseUserAgentString(userAgentString)
        : null;
  }

  public static String getMethod(HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    return request.getMethod().toUpperCase();
  }

  public static String getServerPath(HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    int serverPort = request.getServerPort();
    return serverPort == HTTP_PORT ? String
        .format(FORMATTER_SERVER_PATH_WITH_HTTP_PORT, request.getScheme(), request.getServerName())
        : String.format(FORMATTER_SERVER_PATH, request.getScheme(), request.getServerName(),
            serverPort);
  }

  public static String getRequestURI(HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    return request.getRequestURI();
  }

  public static String getContextPath(HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    return request.getContextPath();
  }

  public static String getServletPath(HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    return request.getServletPath();
  }

  public static String getParameters(HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    if (StringUtils.equals(RequestMethod.GET.name(), request.getMethod().toUpperCase())) {
      return request.getQueryString();
    }
    Map<String, String[]> parameterMap = request.getParameterMap();
    if (parameterMap == null || parameterMap.isEmpty()) {
      return StringUtils.EMPTY;
    }
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String[]> parameterEntry : parameterMap.entrySet()) {
      String parameterKey = parameterEntry.getKey();
      String[] parameterValues = parameterEntry.getValue();
      for (String parameterValue : parameterValues) {
        sb.append(SEPARATOR_PARAMETER)
            .append(String.format(FORMATTER_PARAMETER, parameterKey, parameterValue));
      }
    }
    return sb.deleteCharAt(0).toString();
  }

  public static String getUrl(HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    return getServerPath(request) + getRequestURI(request);
  }

  public static String concatUrlAndParameters(String url, String parameters) {
    if (StringUtils.isEmpty(parameters)) {
      return url;
    }
    return url + SEPARATOR_URL_AND_PARAMETERS + parameters;
  }

  public static String getUrlAndParameters(HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    return concatUrlAndParameters(getUrl(request), getParameters(request));
  }

  public static boolean isXMLHttpRequest(HttpServletRequest request) {
    if (request == null) {
      return false;
    }
    return StringUtils
        .equalsIgnoreCase(XML_HTTP_REQUEST, request.getHeader(HEADER_X_REQUESTED_WITH));
  }

  public static boolean isMobileOrTablet(HttpServletRequest request) {
    DeviceType deviceType = getUserAgent(request).getOperatingSystem().getDeviceType();
    return deviceType == DeviceType.MOBILE || deviceType == DeviceType.TABLET;
  }

  public static HttpServletRequest currentRequest() {
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes();
    return attr != null ? attr.getRequest() : null;
  }

  public static HttpSession currentSession() {
    HttpServletRequest request = currentRequest();
    return request != null ? request.getSession() : null;
  }

}
