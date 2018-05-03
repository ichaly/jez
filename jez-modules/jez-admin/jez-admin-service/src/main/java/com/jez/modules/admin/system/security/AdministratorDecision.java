package com.jez.modules.admin.system.security;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.Group;

/**
 * Created by JEZ on 2017/6/4.
 */
public interface AdministratorDecision {

  boolean isAdministrator(Long userId) throws ServiceException;

  boolean isAdministratorGroup(Group group) throws ServiceException;

  boolean isAdministratorGroup(Long groupId) throws ServiceException;

}
