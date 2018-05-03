package com.jez.core.web.security.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;

/**
 * Created by JEZ on 2017/5/28.
 */
public class HttpStatusEntryPoint implements AuthenticationEntryPoint {

  private final HttpStatus httpStatusToReturn;

  public HttpStatusEntryPoint(HttpStatus httpStatusToReturn) {
    Assert.notNull(httpStatusToReturn, "The provided HttpStatus must not be null.");
    this.httpStatusToReturn = httpStatusToReturn;
  }

  public HttpStatusEntryPoint() {
    this.httpStatusToReturn = HttpStatus.UNAUTHORIZED;
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    response.setStatus(authException instanceof LockedException ? HttpStatus.LOCKED.value()
        : httpStatusToReturn.value());
    if (authException.getMessage() == null) {
      return;
    }
    PrintWriter out = response.getWriter();
    out.write(authException.getMessage());
    out.flush();
  }
}
