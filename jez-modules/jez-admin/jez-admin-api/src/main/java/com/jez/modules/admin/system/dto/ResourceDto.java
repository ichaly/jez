package com.jez.modules.admin.system.dto;

import com.jez.modules.admin.system.entity.Resource;

/**
 * Created by JEZ on 2017/7/24.
 */
public class ResourceDto extends Resource {

  private static final long serialVersionUID = -3843500540520438633L;

  private boolean updateOrder;

  private boolean reverse;

  private boolean end;

  public boolean isUpdateOrder() {
    return updateOrder;
  }

  public void setUpdateOrder(boolean updateOrder) {
    this.updateOrder = updateOrder;
  }

  public boolean isReverse() {
    return reverse;
  }

  public void setReverse(boolean reverse) {
    this.reverse = reverse;
  }

  public boolean isEnd() {
    return end;
  }

  public void setEnd(boolean end) {
    this.end = end;
  }
}
