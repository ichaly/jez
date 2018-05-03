package com.jez.core.web.security.cors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JEZ on 2017/6/22.
 */
@Controller
public class CorsAuthenticationController {

  @ResponseBody
  @RequestMapping("${application.security.loginProcessingUrl: '/j_spring_security_check'}")
  public void corsProcessingLogin() throws Exception {
  }

  @ResponseBody
  @RequestMapping("${application.security.logoutUrl: '/logout'}")
  public void corsLogout() throws Exception {
  }

}
