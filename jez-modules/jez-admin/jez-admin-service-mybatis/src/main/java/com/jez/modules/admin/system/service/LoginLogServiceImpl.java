package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.data.mybatis.service.AbstractServiceSupport;
import com.jez.modules.admin.system.entity.LoginLog;
import com.jez.modules.admin.system.example.LoginLogExample;
import com.jez.modules.admin.system.example.LoginLogExample.Criteria;
import com.jez.modules.admin.system.mapper.LoginLogMapper;
import com.jez.modules.admin.system.query.LoginLogQuery;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/7/17.
 */
@Service
@Transactional(readOnly = true)
public class LoginLogServiceImpl extends
    AbstractServiceSupport<LoginLog, LoginLogQuery, LoginLogExample, LoginLogMapper> implements
    LoginLogService {

  @Override
  @Transactional
  public void logLogin(Long userId, String sessionId, Date loginTime, String clientIp,
      String clientDevice, String clientOs, String clientBrowser, String clientBrowserVersion)
      throws ServiceException {
    logLogout(userId, loginTime);
    LoginLog loginLog = new LoginLog();
    loginLog.setUserId(userId);
    loginLog.setSessionId(sessionId);
    loginLog.setLoginTime(loginTime);
    loginLog.setClientIp(clientIp);
    loginLog.setClientDevice(clientDevice);
    loginLog.setClientOs(clientOs);
    loginLog.setClientBrowser(clientBrowser);
    loginLog.setClientBrowserVersion(clientBrowserVersion);
    mapper.insert(loginLog);
  }

  @Override
  @Transactional
  public void logLogout(Long userId, Date logoutTime) throws ServiceException {
    LoginLogExample example = new LoginLogExample();
    example.createCriteria().andUserIdEqualTo(userId).andLogoutTimeIsNull();
    LoginLog loginLog = new LoginLog();
    loginLog.setLogoutTime(logoutTime);
    mapper.updateByExampleSelective(loginLog, example);
  }

  @Override
  @Transactional
  public void logLogout(String sessionId, Date logoutTime) throws ServiceException {
    LoginLogExample example = new LoginLogExample();
    example.createCriteria().andSessionIdEqualTo(sessionId).andLogoutTimeIsNull();
    LoginLog loginLog = new LoginLog();
    loginLog.setLogoutTime(logoutTime);
    mapper.updateByExampleSelective(loginLog, example);
  }

  @Override
  public List<LoginLog> findUnLogoutByUserId(Long userId) {
    LoginLogExample example = new LoginLogExample();
    example.createCriteria().andUserIdEqualTo(userId).andLogoutTimeIsNull();
    return mapper.selectByExample(example);
  }

  @Override
  protected LoginLogExample convertToExample(LoginLogQuery loginLogQuery) throws ServiceException {
    LoginLogExample example = new LoginLogExample();
    example.setOrderByClause("ll.login_time DESC");
    Criteria criteria = example.createCriteria();
    if (StringUtils.isNotEmpty(loginLogQuery.getClientIp())) {
      criteria.andClientIpLike(toLikeParameter(loginLogQuery.getClientIp()));
    }
    if (loginLogQuery.getLoginTimeFrom() != null) {
      criteria.andLoginTimeGreaterThanOrEqualTo(loginLogQuery.getLoginTimeFrom());
    }
    if (loginLogQuery.getLoginTimeTo() != null) {
      criteria.andLoginTimeLessThanOrEqualTo(loginLogQuery.getLoginTimeTo());
    }
    if (StringUtils.isNotEmpty(loginLogQuery.getUserUsername())) {
      criteria.andUserUsernameLike(toLikeParameter(loginLogQuery.getUserUsername()));
    }
    if (StringUtils.isNotEmpty(loginLogQuery.getUserNickname())) {
      criteria.andUserNicknameLike(toLikeParameter(loginLogQuery.getUserNickname()));
    }
    return example;
  }

}
