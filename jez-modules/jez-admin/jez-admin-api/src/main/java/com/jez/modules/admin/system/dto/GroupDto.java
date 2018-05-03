package com.jez.modules.admin.system.dto;

import com.jez.modules.admin.system.entity.Group;

/**
 * Created by JEZ on 2017/7/10.
 */
public class GroupDto extends Group {

  private static final long serialVersionUID = -3858227343764225587L;

  private boolean updateResources;

  private Long[] resourceIds;

  public boolean isUpdateResources() {
    return updateResources;
  }

  public void setUpdateResources(boolean updateResources) {
    this.updateResources = updateResources;
  }

  public Long[] getResourceIds() {
    return resourceIds;
  }

  public void setResourceIds(Long[] resourceIds) {
    this.resourceIds = resourceIds;
  }
}
