package com.jez.modules.admin.system.service;

import com.jez.core.ServiceError;
import com.jez.core.ServiceException;
import com.jez.data.mybatis.service.AbstractAuditingNestedSetServiceSupport;
import com.jez.modules.admin.system.constant.SystemCaches;
import com.jez.modules.admin.system.entity.Office;
import com.jez.modules.admin.system.example.OfficeExample;
import com.jez.modules.admin.system.example.OfficeExample.Criteria;
import com.jez.modules.admin.system.mapper.OfficeMapper;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/5/16.
 */
@Service
@Transactional(readOnly = true)
public class OfficeServiceImpl extends
    AbstractAuditingNestedSetServiceSupport<Office, Office, OfficeExample, OfficeMapper> implements
    OfficeService {

  @Override
  protected OfficeExample convertToRtEqualToExample(Long rt) {
    OfficeExample example = new OfficeExample();
    example.createCriteria().andRtEqualTo(rt);
    return example;
  }

  @Override
  protected OfficeExample convertToLftEqualToExample(Long lft) {
    OfficeExample example = new OfficeExample();
    example.createCriteria().andLftEqualTo(lft);
    return example;
  }

  @Override
  protected OfficeExample convertToBetweenExample(Long lft, Long rt) {
    OfficeExample example = new OfficeExample();
    example.createCriteria().andLftGreaterThanOrEqualTo(lft).andRtLessThanOrEqualTo(rt);
    return example;
  }

  @Override
  protected void preCreate(Office office) throws ServiceException {
    if (!isUnique(null, office.getCode())) {
      ServiceError.ENTITY_EXISTS.raise();
    }
    super.preCreate(office);
  }

  @Override
  @Transactional
  @CacheEvict(value = SystemCaches.SYSTEM_OFFICE, allEntries = true)
  public void create(Office office) throws ServiceException {
    super.create(office);
  }

  @Override
  protected void preUpdate(Office office) throws ServiceException {
    super.preUpdate(office);
    if (office.getCode() != null && !isUnique(null, office.getCode())) {
      ServiceError.ENTITY_EXISTS.raise();
    }
  }

  @Override
  @Transactional
  @CacheEvict(value = SystemCaches.SYSTEM_OFFICE, allEntries = true)
  public void update(Office office) throws ServiceException {
    super.update(office);
  }

  @Override
  @Transactional
  @CacheEvict(value = SystemCaches.SYSTEM_OFFICE, allEntries = true)
  public void delete(Long id) throws ServiceException {
    super.delete(id);
  }

  @Override
  @Transactional
  @CacheEvict(value = SystemCaches.SYSTEM_OFFICE, allEntries = true)
  public void shift(Long id, boolean reverse, boolean end) throws ServiceException {
    super.shift(id, reverse, end);
  }

  @Override
  protected OfficeExample convertToExample(Office office) throws ServiceException {
    OfficeExample example = getDefaultOrderByExample();
    Criteria criteria = example.createCriteria();
    if (office.getType() != null) {
      criteria.andTypeEqualTo(office.getType());
    }
    if (StringUtils.isNotEmpty(office.getName())) {
      criteria.andNameLike(toLikeParameter(office.getName()));
    }
    if (StringUtils.isNotEmpty(office.getCode())) {
      criteria.andCodeLike(toLikeParameter(office.getCode()));
    }
    if (StringUtils.isNotEmpty(office.getAddress())) {
      criteria.andCodeLike(toLikeParameter(office.getAddress()));
    }
    return example;
  }

  @Override
  public boolean isUnique(Long id, String code) throws ServiceException {
    OfficeExample example = new OfficeExample();
    Criteria criteria = example.createCriteria();
    criteria.andCodeEqualTo(code);
    if (id != null) {
      criteria.andIdNotEqualTo(id);
    }
    return mapper.countByExample(example) == 0L;
  }


  @Override
  @Cacheable(value = SystemCaches.SYSTEM_OFFICE, key = "'all'")
  public List<Office> findAll() throws ServiceException {
    return mapper.selectByExample(getDefaultOrderByExample());
  }

  private OfficeExample getDefaultOrderByExample() {
    OfficeExample example = new OfficeExample();
    example.setOrderByClause("o.lft");
    return example;
  }

}
