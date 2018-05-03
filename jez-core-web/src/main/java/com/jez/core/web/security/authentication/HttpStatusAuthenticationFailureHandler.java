package com.jez.core.web.security.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.Assert;

/**
 * Created by JEZ on 2017/5/22.
 */
public class HttpStatusAuthenticationFailureHandler implements AuthenticationFailureHandler {

  private final HttpStatus httpStatusToReturn;

  public HttpStatusAuthenticationFailureHandler(HttpStatus httpStatusToReturn) {
    Assert.notNull(httpStatusToReturn, "The provided HttpStatus must not be null.");
    this.httpStatusToReturn = httpStatusToReturn;
  }

  public HttpStatusAuthenticationFailureHandler() {
    this.httpStatusToReturn = HttpStatus.UNAUTHORIZED;
  }

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    response.setStatus(exception instanceof AccountStatusException ? HttpStatus.LOCKED.value()
        : this.httpStatusToReturn.value());
    if (exception.getMessage() == null) {
      return;
    }
    PrintWriter out = response.getWriter();
    out.write(exception.getMessage());
    out.flush();
  }

}
