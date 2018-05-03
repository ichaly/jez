package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.LoginLog;
import com.jez.modules.admin.system.query.LoginLogQuery;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by JEZ on 2017/7/17.
 */
public interface LoginLogService {

  void logLogin(Long userId, String sessionId, Date loginTime, String clientIp, String clientDevice,
      String clientOs, String clientBrowser, String clientBrowserVersion) throws ServiceException;

  void logLogout(Long userId, Date logoutTime) throws ServiceException;

  void logLogout(String sessionId, Date logoutTime) throws ServiceException;

  Page<LoginLog> findPage(LoginLogQuery loginLogQuery, Pageable pageable) throws ServiceException;

  LoginLog get(Long id) throws ServiceException;

  List<LoginLog> findUnLogoutByUserId(Long userId);

}
