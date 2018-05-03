package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.OperationLog;
import com.jez.modules.admin.system.query.OperationLogQuery;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by JEZ on 2017/7/16.
 */
public interface OperationLogService {

  void logOperation(Long userId, Date operateTime, String pathExp, String method, String serverPath,
      String servletPath, String headers, String parameters, String payload, Integer responseStatus,
      Long responseMillis, String clientIp, String clientDevice, String clientOs,
      String clientBrowser, String clientBrowserVersion) throws ServiceException;

  Page<OperationLog> findPage(OperationLogQuery operationLogQuery, Pageable pageable)
      throws ServiceException;

  OperationLog get(Long id) throws ServiceException;

}
