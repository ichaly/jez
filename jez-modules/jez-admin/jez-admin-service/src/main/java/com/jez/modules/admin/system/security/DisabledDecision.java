package com.jez.modules.admin.system.security;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.Group;

/**
 * Created by JEZ on 2017/6/4.
 */
public class DisabledDecision implements AdministratorDecision {

  @Override
  public boolean isAdministrator(Long userId) {
    return false;
  }

  @Override
  public boolean isAdministratorGroup(Group group) throws ServiceException {
    return false;
  }

  @Override
  public boolean isAdministratorGroup(Long groupId) throws ServiceException {
    return false;
  }

}
