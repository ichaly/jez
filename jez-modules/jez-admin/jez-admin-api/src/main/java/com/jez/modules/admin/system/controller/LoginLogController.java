package com.jez.modules.admin.system.controller;

import com.jez.modules.admin.system.entity.LoginLog;
import com.jez.modules.admin.system.query.LoginLogQuery;
import com.jez.modules.admin.system.service.LoginLogService;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JEZ on 2017/7/10.
 */
@RestController
@RequestMapping("/system/login-logs")
public class LoginLogController {

  @Resource
  private LoginLogService loginLogService;

  @PreAuthorize("hasAuthority('system:login-logs:query')")
  @GetMapping
  public Page<LoginLog> findPage(@ModelAttribute LoginLogQuery loginLogQuery, Pageable pageable)
      throws Exception {
    return loginLogService.findPage(loginLogQuery, pageable);
  }


  @PreAuthorize("hasAuthority('system:login-logs:query')")
  @GetMapping("/{id}")
  public LoginLog get(@PathVariable Long id) throws Exception {
    return loginLogService.get(id);
  }

}
