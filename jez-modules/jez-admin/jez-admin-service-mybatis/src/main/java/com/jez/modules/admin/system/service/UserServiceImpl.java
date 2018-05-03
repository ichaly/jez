package com.jez.modules.admin.system.service;

import com.jez.core.ServiceError;
import com.jez.core.ServiceException;
import com.jez.core.persistence.enums.EnableStatus;
import com.jez.core.persistence.enums.Sex;
import com.jez.data.mybatis.service.AbstractAuditingServiceSupport;
import com.jez.modules.admin.system.constant.SystemCaches;
import com.jez.modules.admin.system.entity.GroupUser;
import com.jez.modules.admin.system.entity.HistoryUser;
import com.jez.modules.admin.system.entity.User;
import com.jez.modules.admin.system.example.GroupUserExample;
import com.jez.modules.admin.system.example.OfficeExample;
import com.jez.modules.admin.system.example.UserExample;
import com.jez.modules.admin.system.example.UserExample.Criteria;
import com.jez.modules.admin.system.mapper.GroupUserMapper;
import com.jez.modules.admin.system.mapper.HistoryUserMapper;
import com.jez.modules.admin.system.mapper.OfficeMapper;
import com.jez.modules.admin.system.mapper.UserMapper;
import com.jez.modules.admin.system.security.AdministratorDecision;
import com.jez.modules.admin.system.security.DisabledDecision;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/5/18.
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends
    AbstractAuditingServiceSupport<User, User, UserExample, UserMapper> implements UserService,
    ApplicationContextAware {

  @Resource
  private OfficeMapper officeMapper;

  @Resource
  private HistoryUserMapper historyUserMapper;

  @Resource
  private GroupUserMapper groupUserMapper;

  private AdministratorDecision administratorDecision;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    try {
      administratorDecision = applicationContext.getBean(AdministratorDecision.class);
    } catch (BeansException e) {
      administratorDecision = new DisabledDecision();
    }
  }

  private void verifyOfficeId(Long officeId) throws ServiceException {
    OfficeExample example = new OfficeExample();
    example.createCriteria().andIdEqualTo(officeId);
    if (officeMapper.countByExample(example) == 0L) {
      ServiceError.ENTITY_NOT_FOUND.raise();
    }
  }

  @Override
  protected void preCreate(User user) throws ServiceException {
    super.preCreate(user);
    if (!isUnique(null, user.getUsername())) {
      ServiceError.ENTITY_EXISTS.raise();
    }
    verifyOfficeId(user.getOfficeId());
    if (user.getSex() == null) {
      user.setSex(Sex.MALE.getValue());
    }
  }

  @Override
  protected void postCreate(User user) throws ServiceException {
    super.postCreate(user);
    HistoryUser historyUser = new HistoryUser();
    BeanUtils.copyProperties(user, historyUser);
    historyUserMapper.insertSelective(historyUser);
  }

  @Override
  protected void preUpdate(User user) throws ServiceException {
    super.preUpdate(user);
    if (administratorDecision.isAdministrator(user.getId()) && !administratorDecision
        .isAdministrator(auditorAware.getCurrentAuditor())) {
      ServiceError.ADMINISTRATOR_REQUIRED.raise();
    }
    if (user.getOfficeId() != null) {
      verifyOfficeId(user.getOfficeId());
    }
    user.setUsername(null);
  }

  @Override
  protected void postUpdate(User user) throws ServiceException {
    super.postUpdate(user);
    HistoryUser historyUser = new HistoryUser();
    BeanUtils.copyProperties(user, historyUser);
    historyUserMapper.updateByPrimaryKeySelective(historyUser);
  }

  @Override
  protected void preDelete(Long id) throws ServiceException {
    super.preDelete(id);
    if (administratorDecision.isAdministrator(id) && !administratorDecision
        .isAdministrator(auditorAware.getCurrentAuditor())) {
      ServiceError.ADMINISTRATOR_REQUIRED.raise();
    }
  }

  @Override
  public User get(Long id) throws ServiceException {
    return super.get(id);
  }

  @Override
  @Cacheable(value = SystemCaches.SYSTEM_USER, key = "#id.toString()")
  public User getCache(Long id) throws ServiceException {
    return super.get(id);
  }

  @Override
  public User getByUsername(String username) throws ServiceException {
    UserExample example = new UserExample();
    example.createCriteria().andUsernameEqualTo(username);
    User user = getOne(mapper.selectByExample(example));
    if (user == null) {
      ServiceError.ENTITY_NOT_FOUND.raise();
    }
    return user;
  }

  @Override
  @Transactional
  public void disableByUsername(String username) throws ServiceException {
    User target = getByUsername(username);
    User user = new User();
    user.setId(target.getId());
    user.setLastModifiedDate(dateTimeProvider.getNow().getTime());
    user.setStatus(EnableStatus.DISABLED.getValue());
    if (mapper.updateByPrimaryKeySelective(user) == 0) {
      ServiceError.ENTITY_NOT_FOUND.raise();
    }
    HistoryUser historyUser = new HistoryUser();
    BeanUtils.copyProperties(user, historyUser);
    historyUserMapper.updateByPrimaryKeySelective(historyUser);
  }

  @Override
  public boolean isUnique(Long id, String username) throws ServiceException {
    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();
    criteria.andUsernameEqualTo(username);
    if (id != null) {
      criteria.andIdNotEqualTo(id);
    }
    return mapper.countByExample(example) == 0L;
  }

  @Override
  @Transactional
  public void create(User user, Long[] groupIds) throws ServiceException {
    create(user);
    assignGroups(user.getId(), groupIds,
        administratorDecision.isAdministrator(auditorAware.getCurrentAuditor()));
  }

  @Override
  @Cacheable(value = SystemCaches.SYSTEM_USER, key = "#id.toString()")
  public void delete(Long id) throws ServiceException {
    super.delete(id);
  }

  @Override
  @Transactional
  @CacheEvict(value = SystemCaches.SYSTEM_USER, key = "#user.id.toString()")
  public void update(User user) throws ServiceException {
    super.update(user);
  }

  @Override
  @Transactional
  @CacheEvict(value = {SystemCaches.SYSTEM_AUTHORITY, SystemCaches.SYSTEM_USER,
      SystemCaches.SYSTEM_GROUP, SystemCaches.SYSTEM_RESOURCE}, key = "#user.id.toString()")
  public void update(User user, Long[] groupIds) throws ServiceException {
    update(user);
    clearGroups(user.getId());
    assignGroups(user.getId(), groupIds,
        administratorDecision.isAdministrator(auditorAware.getCurrentAuditor()));
  }

  private void clearGroups(Long id) {
    GroupUserExample example = new GroupUserExample();
    example.createCriteria().andUserIdEqualTo(id);
    groupUserMapper.deleteByExample(example);
  }

  private void assignGroups(Long id, Long[] groupIds, boolean isAdministrator)
      throws ServiceException {
    if (groupIds == null || groupIds.length == 0) {
      return;
    }
    for (Long groupId : groupIds) {
      if (administratorDecision.isAdministratorGroup(groupId) && !isAdministrator) {
        ServiceError.ADMINISTRATOR_REQUIRED.raise();
      }
      GroupUser groupUser = new GroupUser();
      groupUser.setUserId(id);
      groupUser.setGroupId(groupId);
      groupUserMapper.insert(groupUser);
    }
  }

}
