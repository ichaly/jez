package com.jez.core.web.handler;

import com.jez.core.ServiceError;
import com.jez.core.ServiceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by JEZ on 2017/5/21.
 */
@ControllerAdvice
public class DefaultExceptionHandler {

  public static final int HTTP_STATUS_VALIDATION_ERROR = 484;

  public static final int HTTP_STATUS_SERVICE_ERROR = 520;

  private static Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

  @ExceptionHandler(LockedException.class)
  @ResponseStatus(HttpStatus.LOCKED)
  @ResponseBody
  public String lockedException(Exception e) {
    return e.getMessage();
  }

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public String authenticationException(Exception e) {
    return e.getMessage();
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public String accessDeniedException(Exception e) {
    return e.getMessage();
  }

  @ExceptionHandler(DataAccessException.class)
  public void dataAccessException() throws ServiceException {
    ServiceError.DATA_ACCESS.raise();
  }

  @ExceptionHandler(ServletRequestBindingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public String servletRequestBindingException(Exception e) {
    return e.getMessage();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public List<Map<String, String>> methodArgumentNotValidException(HttpServletResponse response,
      Exception e) {
    logger.error("Service error.", e);
    response.setStatus(HTTP_STATUS_VALIDATION_ERROR);
    List<Map<String, String>> list = new ArrayList<>();
    MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
    methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(
        fe -> {
          Map<String, String> map = new HashMap<>();
          map.put("field", fe.getField());
          map.put("message", fe.getDefaultMessage());
          list.add(map);
        });
    return list;
  }

  @ExceptionHandler(ServiceException.class)
  @ResponseBody
  public String serviceException(HttpServletResponse response, Exception e) {
    logger.error("Service error.", e);
    response.setStatus(HTTP_STATUS_SERVICE_ERROR);
    return e.getMessage();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public String exception(Exception e) {
    logger.error("Error.", e);
    return e.getMessage();
  }

}
