package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.data.mybatis.service.AbstractServiceSupport;
import com.jez.modules.admin.system.entity.OperationLog;
import com.jez.modules.admin.system.example.OperationLogExample;
import com.jez.modules.admin.system.example.OperationLogExample.Criteria;
import com.jez.modules.admin.system.mapper.OperationLogMapper;
import com.jez.modules.admin.system.query.OperationLogQuery;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/7/17.
 */
@Service
@Transactional(readOnly = true)
public class OperationLogServiceImpl extends
    AbstractServiceSupport<OperationLog, OperationLogQuery, OperationLogExample, OperationLogMapper> implements
    OperationLogService {

  private static final String TITLE_UNKNOWN = "UNKNOWN";

  @Resource
  private ResourceService resourceService;

  @Override
  @Transactional
  public void logOperation(Long userId, Date operateTime, String pathExp, String method,
      String serverPath,
      String servletPath, String headers, String parameters, String payload, Integer responseStatus,
      Long responseMillis, String clientIp, String clientDevice, String clientOs,
      String clientBrowser, String clientBrowserVersion) throws ServiceException {
    OperationLog operationLog = new OperationLog();
    operationLog.setUserId(userId);
    operationLog.setOperateTime(operateTime);
    String title = resourceService.getTitleMap()
        .get(resourceService.toTitleMapKey(pathExp, method));
    operationLog.setTitle(StringUtils.isNotEmpty(title) ? title : TITLE_UNKNOWN);
    operationLog.setServerPath(serverPath);
    operationLog.setServletPath(servletPath);
    operationLog.setMethod(method);
    operationLog.setHeaders(headers);
    operationLog.setParameters(parameters);
    operationLog.setPayload(payload);
    operationLog.setResponseStatus(responseStatus);
    operationLog.setResponseMillis(responseMillis);
    operationLog.setClientIp(clientIp);
    operationLog.setClientDevice(clientDevice);
    operationLog.setClientOs(clientOs);
    operationLog.setClientBrowser(clientBrowser);
    operationLog.setClientBrowserVersion(clientBrowserVersion);
    mapper.insert(operationLog);
  }

  @Override
  protected OperationLogExample convertToExample(OperationLogQuery operationLogQuery)
      throws ServiceException {
    OperationLogExample example = new OperationLogExample();
    example.setOrderByClause("ol.operate_time DESC");
    Criteria criteria = example.createCriteria();
    if (StringUtils.isNotEmpty(operationLogQuery.getTitle())) {
      criteria.andTitleLike(toLikeParameter(operationLogQuery.getTitle()));
    }
    if (StringUtils.isNotEmpty(operationLogQuery.getServletPath())) {
      criteria.andServletPathLike(toLikeParameter(operationLogQuery.getServletPath()));
    }
    if (StringUtils.isNotEmpty(operationLogQuery.getClientIp())) {
      criteria.andClientIpLike(toLikeParameter(operationLogQuery.getClientIp()));
    }
    if (StringUtils.isNotEmpty(operationLogQuery.getMethod())) {
      criteria.andMethodEqualTo(operationLogQuery.getMethod());
    }
    if (operationLogQuery.getOperateTimeFrom() != null) {
      criteria.andOperateTimeGreaterThanOrEqualTo(operationLogQuery.getOperateTimeFrom());
    }
    if (operationLogQuery.getOperateTimeTo() != null) {
      criteria.andOperateTimeLessThanOrEqualTo(operationLogQuery.getOperateTimeTo());
    }
    if (StringUtils.isNotEmpty(operationLogQuery.getUserUsername())) {
      criteria.andUserUsernameLike(toLikeParameter(operationLogQuery.getUserUsername()));
    }
    if (StringUtils.isNotEmpty(operationLogQuery.getUserNickname())) {
      criteria.andUserNicknameLike(toLikeParameter(operationLogQuery.getUserNickname()));
    }
    return example;
  }

}
