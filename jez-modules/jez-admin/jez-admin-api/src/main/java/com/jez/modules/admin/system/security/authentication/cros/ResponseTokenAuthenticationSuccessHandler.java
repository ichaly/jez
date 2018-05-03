package com.jez.modules.admin.system.security.authentication.cros;

import com.jez.modules.admin.system.security.authentication.LogAuthenticationSuccessHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

/**
 * Created by JEZ on 2017/7/17.
 */
public class ResponseTokenAuthenticationSuccessHandler extends LogAuthenticationSuccessHandler {

  public ResponseTokenAuthenticationSuccessHandler(
      HttpStatus httpStatusToReturn, int numberOfThreads) {
    super(httpStatusToReturn, numberOfThreads);
  }

  public ResponseTokenAuthenticationSuccessHandler(int numberOfThreads) {
    super(numberOfThreads);
  }

  public ResponseTokenAuthenticationSuccessHandler(
      HttpStatus httpStatusToReturn) {
    super(httpStatusToReturn);
  }

  public ResponseTokenAuthenticationSuccessHandler() {
    super();
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    super.onAuthenticationSuccess(request, response, authentication);
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.write(request.getSession().getId());
    out.flush();
  }
}

