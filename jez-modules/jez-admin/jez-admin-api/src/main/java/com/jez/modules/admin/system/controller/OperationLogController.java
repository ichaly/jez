package com.jez.modules.admin.system.controller;

import com.jez.modules.admin.system.entity.OperationLog;
import com.jez.modules.admin.system.query.OperationLogQuery;
import com.jez.modules.admin.system.service.OperationLogService;
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
@RequestMapping("/system/operation-logs")
public class OperationLogController {

  @Resource
  private OperationLogService operationLogService;


  @PreAuthorize("hasAuthority('system:operation-logs:query')")
  @GetMapping
  public Page<OperationLog> findPage(@ModelAttribute OperationLogQuery operationLogQuery,
      Pageable pageable) throws Exception {
    return operationLogService.findPage(operationLogQuery, pageable);
  }


  @PreAuthorize("hasAuthority('system:operation-logs:query')")
  @GetMapping("/{id}")
  public OperationLog get(@PathVariable Long id) throws Exception {
    return operationLogService.get(id);
  }


}
