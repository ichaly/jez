package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.core.persistence.enums.EnableStatus;
import com.jez.data.mybatis.service.AbstractAuditingNestedSetServiceSupport;
import com.jez.modules.admin.system.constant.SystemCaches;
import com.jez.modules.admin.system.entity.Resource;
import com.jez.modules.admin.system.example.ResourceExample;
import com.jez.modules.admin.system.example.ResourceExample.Criteria;
import com.jez.modules.admin.system.mapper.ResourceMapper;
import com.jez.modules.admin.system.security.AdministratorDecision;
import com.jez.modules.admin.system.security.DisabledDecision;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/6/4.
 */
@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl extends
    AbstractAuditingNestedSetServiceSupport<Resource, Resource, ResourceExample, ResourceMapper> implements
    ResourceService, ApplicationContextAware {

  private AdministratorDecision administratorDecision;

  @Override
  protected ResourceExample convertToRtEqualToExample(Long rt) {
    ResourceExample example = new ResourceExample();
    example.createCriteria().andRtEqualTo(rt);
    return example;
  }

  @Override
  protected ResourceExample convertToLftEqualToExample(Long lft) {
    ResourceExample example = new ResourceExample();
    example.createCriteria().andLftEqualTo(lft);
    return example;
  }

  @Override
  protected ResourceExample convertToBetweenExample(Long lft, Long rt) {
    ResourceExample example = new ResourceExample();
    example.createCriteria().andLftGreaterThanOrEqualTo(lft).andRtLessThanOrEqualTo(rt);
    return example;
  }


  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    try {
      administratorDecision = applicationContext.getBean(AdministratorDecision.class);
    } catch (BeansException e) {
      administratorDecision = new DisabledDecision();
    }
  }

  @Override
  @Cacheable(value = SystemCaches.SYSTEM_RESOURCE, key = "#userId.toString()")
  public List<Resource> findByUserId(Long userId) throws ServiceException {
    if (administratorDecision.isAdministrator(userId)) {
      return mapper.selectByExample(getDefaultOrderByExample());
    }
    return mapper.selectByUserIdAndStatus(userId, EnableStatus.ENABLED.getValue());
  }

  @Override
  public Set<String> findPermissionByUserId(Long userId) throws ServiceException {
    if (administratorDecision.isAdministrator(userId)) {
      return mapper.selectPermission();
    }
    return mapper.selectPermissionByUserIdAndStatus(userId, EnableStatus.ENABLED.getValue());
  }

  @Override
  @Transactional
  @CacheEvict(value = {SystemCaches.SYSTEM_RESOURCE,
      SystemCaches.SYSTEM_AUTHORITY}, allEntries = true)
  public void create(Resource resource) throws ServiceException {
    super.create(resource);
  }

  @Override
  @Transactional
  @CacheEvict(value = {SystemCaches.SYSTEM_RESOURCE,
      SystemCaches.SYSTEM_AUTHORITY}, allEntries = true)
  public void update(Resource resource) throws ServiceException {
    super.update(resource);
  }

  @Override
  @Transactional
  @CacheEvict(value = {SystemCaches.SYSTEM_RESOURCE,
      SystemCaches.SYSTEM_AUTHORITY}, allEntries = true)
  public void delete(Long id) throws ServiceException {
    super.delete(id);
  }

  @Override
  @Transactional
  @CacheEvict(value = {SystemCaches.SYSTEM_RESOURCE,
      SystemCaches.SYSTEM_AUTHORITY}, allEntries = true)
  public void shift(Long id, boolean reverse, boolean end) throws ServiceException {
    super.shift(id, reverse, end);
  }

  @Override
  protected ResourceExample convertToExample(Resource resource) throws ServiceException {
    ResourceExample example = getDefaultOrderByExample();
    if (resource != null) {
      Criteria criteria = example.createCriteria();
      if (StringUtils.isNotEmpty(resource.getName())) {
        criteria.andNameLike(toLikeParameter(resource.getName()));
      }
      if (StringUtils.isNotEmpty(resource.getPathExp())) {
        criteria.andPathExpLike(toLikeParameter(resource.getPathExp()));
      }
      if (StringUtils.isNotEmpty(resource.getMethod())) {
        criteria.andMethodEqualTo(resource.getMethod());
      }
      if (resource.getType() != null) {
        criteria.andTypeEqualTo(resource.getType());
      }
      if (resource.getStatus() != null) {
        criteria.andStatusEqualTo(resource.getStatus());
      }
    }
    return example;
  }

  @Override
  @Cacheable(value = SystemCaches.SYSTEM_RESOURCE, key = "'all'")
  public List<Resource> findAll() throws ServiceException {
    return mapper.selectByExample(getDefaultOrderByExample());
  }

  private ResourceExample getDefaultOrderByExample() {
    ResourceExample example = new ResourceExample();
    example.setOrderByClause("r.lft");
    return example;
  }

  @Override
  @Cacheable(value = SystemCaches.SYSTEM_RESOURCE, key = "'titles'")
  public Map<String, String> getTitleMap() throws ServiceException {
    List<Resource> resources = findAll();
    Map<String, String> titleMap = new HashMap<>();
    resources.stream().filter(
        r -> StringUtils.isNotEmpty(r.getPathExp()) && StringUtils.isNotEmpty(r.getMethod()))
        .forEach(r -> titleMap.put(combineTitleMapKey(r.getPathExp(), r.getMethod()), r.getName()));
    return titleMap;
  }

  private String combineTitleMapKey(String pathExp, String method) {
    return method + " " + pathExp;
  }

  @Override
  public String toTitleMapKey(String pathExp, String method) throws ServiceException {
    if (StringUtils.isEmpty(pathExp) || StringUtils.isEmpty(method)) {
      return null;
    }
    return combineTitleMapKey(pathExp, method);
  }

}
