package com.jez.modules.admin.system.dto;

import com.jez.core.validation.groups.Create;
import com.jez.modules.admin.system.entity.User;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by JEZ on 2017/7/9.
 */
public class UserDto extends User {

  private static final long serialVersionUID = 1683443615423032169L;

  private boolean kickOut;

  private boolean updateGroups;

  private Long[] groupIds;

  @NotEmpty(groups = Create.class)
  @Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$")
  private String password;

  public boolean isKickOut() {
    return kickOut;
  }

  public void setKickOut(boolean kickOut) {
    this.kickOut = kickOut;
  }

  public boolean isUpdateGroups() {
    return updateGroups;
  }

  public void setUpdateGroups(boolean updateGroups) {
    this.updateGroups = updateGroups;
  }

  public Long[] getGroupIds() {
    return groupIds;
  }

  public void setGroupIds(Long[] groupIds) {
    this.groupIds = groupIds;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
  }
}
