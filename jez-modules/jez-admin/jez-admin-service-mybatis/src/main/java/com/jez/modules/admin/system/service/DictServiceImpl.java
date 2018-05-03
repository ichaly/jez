package com.jez.modules.admin.system.service;

import com.jez.core.ServiceError;
import com.jez.core.ServiceException;
import com.jez.data.mybatis.service.AbstractAuditingServiceSupport;
import com.jez.modules.admin.system.constant.SystemCaches;
import com.jez.modules.admin.system.entity.Dict;
import com.jez.modules.admin.system.example.DictExample;
import com.jez.modules.admin.system.example.DictExample.Criteria;
import com.jez.modules.admin.system.mapper.DictMapper;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/6/12.
 */
@Service
@Transactional(readOnly = true)
public class DictServiceImpl extends
    AbstractAuditingServiceSupport<Dict, Dict, DictExample, DictMapper> implements DictService {

  @Override
  @Transactional
  @CacheEvict(value = SystemCaches.SYSTEM_DICT, key = "#dict.type")
  public void create(Dict dict) throws ServiceException {
    super.create(dict);
  }

  @Override
  protected void preCreate(Dict dict) throws ServiceException {
    super.preCreate(dict);
    if (!isUnique(null, dict.getType(), dict.getValue())) {
      ServiceError.ENTITY_EXISTS.raise();
    }
  }

  @Override
  @Transactional
  @CacheEvict(value = SystemCaches.SYSTEM_DICT, allEntries = true)
  public void delete(Long id) throws ServiceException {
    super.delete(id);
  }

  @Override
  protected void preUpdate(Dict dict) throws ServiceException {
    super.preUpdate(dict);
    if (dict.getValue() != null) {
      Dict current = get(dict.getId());
      if (!isUnique(dict.getId(), current.getType(), dict.getValue())) {
        ServiceError.ENTITY_EXISTS.raise();
      }
    }
    dict.setType(null);
  }

  @Override
  @Transactional
  @CacheEvict(value = SystemCaches.SYSTEM_DICT, allEntries = true)
  public void update(Dict dict) throws ServiceException {
    super.update(dict);
  }

  @Override
  @Cacheable(value = SystemCaches.SYSTEM_DICT, key = "#type")
  public List<Dict> findByType(String type) throws ServiceException {
    DictExample example = new DictExample();
    example.createCriteria().andTypeEqualTo(type);
    example.setOrderByClause("d.sort");
    return mapper.selectByExample(example);
  }

  @Override
  public Dict get(String type, String value) throws ServiceException {
    DictExample example = new DictExample();
    example.createCriteria().andTypeEqualTo(type).andValueEqualTo(value);
    return getOne(mapper.selectByExample(example));
  }

  @Override
  public boolean isUnique(Long id, String type, String value) throws ServiceException {
    DictExample example = new DictExample();
    Criteria criteria = example.createCriteria();
    criteria.andTypeEqualTo(type);
    criteria.andValueEqualTo(value);
    if (id != null) {
      criteria.andIdNotEqualTo(id);
    }
    return mapper.countByExample(example) == 0L;
  }

  @Override
  protected DictExample convertToExample(Dict dict) throws ServiceException {
    DictExample example = new DictExample();
    Criteria criteria = example.createCriteria();
    if (StringUtils.isNotEmpty(dict.getType())) {
      criteria.andTypeLike(toLikeParameter(dict.getType()));
    }
    if (StringUtils.isNotEmpty(dict.getValue())) {
      criteria.andValueLike(toLikeParameter(dict.getValue()));
    }
    if (StringUtils.isNotEmpty(dict.getLabel())) {
      criteria.andLabelLike(toLikeParameter(dict.getLabel()));
    }
    example.setOrderByClause("d.type, d.sort");
    return example;
  }

}
