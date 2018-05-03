package com.jez.modules.admin.system.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.jez.core.validation.groups.Create;
import com.jez.modules.admin.system.entity.User;

/**
 * Created by JEZ on 2017/7/28.
 */
public class SubjectUserDto extends User {

  private static final long serialVersionUID = -5790447168002906734L;

  private boolean updatePassword;

  private String oldPassword;

  @NotEmpty(groups = Create.class)
  @Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$")
  private String password;

  public boolean isUpdatePassword() {
    return updatePassword;
  }

  public void setUpdatePassword(boolean updatePassword) {
    this.updatePassword = updatePassword;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
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
