package com.jez.core.web.security.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.Assert;

/**
 * Created by JEZ on 2017/5/22.
 */
public class HttpStatusLogoutSuccessHandler implements LogoutSuccessHandler {

  private final HttpStatus httpStatusToReturn;

  public HttpStatusLogoutSuccessHandler(HttpStatus httpStatusToReturn) {
    Assert.notNull(httpStatusToReturn, "The provided HttpStatus must not be null.");
    this.httpStatusToReturn = httpStatusToReturn;
  }

  public HttpStatusLogoutSuccessHandler() {
    this.httpStatusToReturn = HttpStatus.OK;
  }

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    response.setStatus(this.httpStatusToReturn.value());
  }
}
