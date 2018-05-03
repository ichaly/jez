package com.jez.modules.admin.system.service;

import com.jez.core.ServiceError;
import com.jez.core.ServiceException;
import com.jez.data.mybatis.service.AbstractAuditingServiceSupport;
import com.jez.modules.admin.system.constant.SystemCaches;
import com.jez.modules.admin.system.entity.Group;
import com.jez.modules.admin.system.entity.GroupResource;
import com.jez.modules.admin.system.example.GroupExample;
import com.jez.modules.admin.system.example.GroupExample.Criteria;
import com.jez.modules.admin.system.example.GroupResourceExample;
import com.jez.modules.admin.system.mapper.GroupMapper;
import com.jez.modules.admin.system.mapper.GroupResourceMapper;
import com.jez.modules.admin.system.security.AdministratorDecision;
import com.jez.modules.admin.system.security.DisabledDecision;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
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
public class GroupServiceImpl extends
    AbstractAuditingServiceSupport<Group, Group, GroupExample, GroupMapper> implements GroupService,
    ApplicationContextAware {

  private AdministratorDecision administratorDecision;

  @Resource
  private GroupResourceMapper groupResourceMapper;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    try {
      administratorDecision = applicationContext.getBean(AdministratorDecision.class);
    } catch (BeansException e) {
      administratorDecision = new DisabledDecision();
    }
  }

  @Override
  @Cacheable(value = SystemCaches.SYSTEM_GROUP, key = "#userId.toString()")
  public List<Group> findByUserId(Long userId) throws ServiceException {
    return mapper.selectByUserId(userId);
  }

  @Override
  public Set<String> findCodeByUserId(Long userId) throws ServiceException {
    return mapper.selectCodeByUserId(userId);
  }

  @Override
  protected void preCreate(Group group) throws ServiceException {
    super.preCreate(group);
    if (!isUnique(null, group.getCode())) {
      ServiceError.ENTITY_EXISTS.raise();
    }
  }

  @Override
  protected void postCreate(Group group) throws ServiceException {
    super.postCreate(group);
    if (administratorDecision.isAdministratorGroup(group.getId()) && !administratorDecision
        .isAdministrator(auditorAware.getCurrentAuditor())) {
      ServiceError.ADMINISTRATOR_REQUIRED.raise();
    }
  }

  @Override
  protected void preUpdate(Group group) throws ServiceException {
    super.preUpdate(group);
    if (administratorDecision.isAdministratorGroup(group.getId()) && !administratorDecision
        .isAdministrator(auditorAware.getCurrentAuditor())) {
      ServiceError.ADMINISTRATOR_REQUIRED.raise();
    }
    if (group.getCode() != null && !isUnique(group.getId(), group.getCode())) {
      ServiceError.ENTITY_EXISTS.raise();
    }
  }

  @Override
  @Transactional
  @CacheEvict(value = {SystemCaches.SYSTEM_GROUP, SystemCaches.SYSTEM_AUTHORITY}, allEntries = true)
  public void update(Group group) throws ServiceException {
    super.update(group);
  }

  @Override
  @Transactional
  @CacheEvict(value = {SystemCaches.SYSTEM_GROUP, SystemCaches.SYSTEM_AUTHORITY}, allEntries = true)
  public void delete(Long id) throws ServiceException {
    super.delete(id);
  }

  @Override
  protected GroupExample convertToExample(Group group) throws ServiceException {
    GroupExample example = new GroupExample();
    example.setOrderByClause("g.code");
    return example;
  }

  @Override
  public boolean isUnique(Long id, String code) throws ServiceException {
    GroupExample example = new GroupExample();
    Criteria criteria = example.createCriteria();
    criteria.andCodeEqualTo(code);
    if (id != null) {
      criteria.andIdNotEqualTo(id);
    }
    return mapper.countByExample(example) == 0L;
  }

  @Override
  @Cacheable(value = SystemCaches.SYSTEM_GROUP, key = "'all'")
  public List<Group> findAll() throws ServiceException {
    GroupExample example = new GroupExample();
    example.setOrderByClause("g.code");
    return mapper.selectByExample(example);
  }

  @Override
  @Transactional
  public void create(Group group, Long[] resourceIds) throws ServiceException {
    create(group);
    assignResources(group.getId(), resourceIds);
  }

  @Override
  @Transactional
  @CacheEvict(value = {SystemCaches.SYSTEM_GROUP, SystemCaches.SYSTEM_AUTHORITY}, allEntries = true)
  public void update(Group group, Long[] resourceIds) throws ServiceException {
    update(group);
    clearResources(group.getId());
    assignResources(group.getId(), resourceIds);
  }

  private void clearResources(Long id) {
    GroupResourceExample example = new GroupResourceExample();
    example.createCriteria().andGroupIdEqualTo(id);
    groupResourceMapper.deleteByExample(example);
  }

  private void assignResources(Long id, Long[] resourceIds)
      throws ServiceException {
    if (resourceIds == null || resourceIds.length == 0) {
      return;
    }
    for (Long resourceId : resourceIds) {
      GroupResource groupResource = new GroupResource();
      groupResource.setGroupId(id);
      groupResource.setResourceId(resourceId);
      groupResourceMapper.insert(groupResource);
    }
  }

}
