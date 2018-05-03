package com.jez.modules.admin.system.dto;

import com.jez.modules.admin.system.entity.Group;
import com.jez.modules.admin.system.entity.Resource;
import com.jez.modules.admin.system.entity.User;
import java.io.Serializable;
import java.util.List;

/**
 * Created by JEZ on 2017/7/24.
 */
public class Subject implements Serializable {

  private static final long serialVersionUID = 5577420256287243483L;

  private User user;

  private List<Resource> resources;

  private List<Group> groups;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Resource> getResources() {
    return resources;
  }

  public void setResources(List<Resource> resources) {
    this.resources = resources;
  }

  public List<Group> getGroups() {
    return groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }
}
