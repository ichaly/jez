package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.data.mybatis.service.AbstractAuditingServiceSupport;
import com.jez.modules.admin.system.constant.SystemCaches;
import com.jez.modules.admin.system.entity.Config;
import com.jez.modules.admin.system.example.ConfigExample;
import com.jez.modules.admin.system.mapper.ConfigMapper;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/12/1.
 */
@Service
@Transactional(readOnly = true)
public class ConfigServiceImpl
    extends AbstractAuditingServiceSupport<Config, Config, ConfigExample, ConfigMapper>
    implements ConfigService {

  @Override
  public Config get(String code) throws ServiceException {
    List<Config> configs = findByCode(code);
    return CollectionUtils.isNotEmpty(configs) ? configs.get(0) : null;
  }

  @Override
  @Cacheable(value = SystemCaches.SYSTEM_CONFIG, key = "#code")
  public List<Config> findByCode(String code) throws ServiceException {
    ConfigExample example = getDefaultOrderByExample();
    example.createCriteria().andCodeEqualTo(code);
    return mapper.selectByExampleWithBLOBs(example);
  }

  private ConfigExample getDefaultOrderByExample() {
    ConfigExample example = new ConfigExample();
    example.setOrderByClause("c.sort");
    return example;
  }

}
