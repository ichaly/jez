package com.jez.modules.admin.system.security;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.Group;
import com.jez.modules.admin.system.service.GroupService;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * Created by JEZ on 2017/6/4.
 */
public class GroupCodeDecision implements AdministratorDecision {

  @Resource
  private GroupService groupService;

  private Set<String> groupCodes;

  public GroupCodeDecision(String... groupCodes) {
    Assert.isTrue(StringUtils.isNoneBlank(groupCodes), "Group codes must not be null");
    this.groupCodes = new HashSet<>();
    Stream.of(groupCodes).forEach(gc -> this.groupCodes.add(gc));
  }

  @Override
  public boolean isAdministrator(Long userId) throws ServiceException {
    Set<String> groups = groupService.findCodeByUserId(userId);
    for (String group : this.groupCodes) {
      if (groups.contains(group)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isAdministratorGroup(Group group) throws ServiceException {
    return group != null && this.groupCodes.contains(group.getCode());
  }

  @Override
  public boolean isAdministratorGroup(Long groupId) throws ServiceException {
    return isAdministratorGroup(groupService.get(groupId));
  }
}
