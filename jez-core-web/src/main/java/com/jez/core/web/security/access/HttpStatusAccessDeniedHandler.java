package com.jez.core.web.security.access;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.Assert;

/**
 * Created by JEZ on 2017/5/22.
 */
public class HttpStatusAccessDeniedHandler implements AccessDeniedHandler {

  private final HttpStatus httpStatusToReturn;

  public HttpStatusAccessDeniedHandler(HttpStatus httpStatusToReturn) {
    Assert.notNull(httpStatusToReturn, "The provided HttpStatus must not be null.");
    this.httpStatusToReturn = httpStatusToReturn;
  }

  public HttpStatusAccessDeniedHandler() {
    this.httpStatusToReturn = HttpStatus.FORBIDDEN;
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.setStatus(this.httpStatusToReturn.value());
    if (accessDeniedException.getMessage() == null) {
      return;
    }
    PrintWriter out = response.getWriter();
    out.write(accessDeniedException.getMessage());
    out.flush();
  }
}
