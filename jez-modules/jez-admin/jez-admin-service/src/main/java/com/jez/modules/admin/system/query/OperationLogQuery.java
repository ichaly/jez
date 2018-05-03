package com.jez.modules.admin.system.query;

import com.jez.modules.admin.system.entity.OperationLog;
import java.util.Date;

/**
 * Created by JEZ on 2017/10/23.
 */
public class OperationLogQuery extends OperationLog {

  private static final long serialVersionUID = -732386389639435215L;

  private Date operateTimeFrom;

  private Date operateTimeTo;

  public Date getOperateTimeFrom() {
    return operateTimeFrom;
  }

  public void setOperateTimeFrom(Date operateTimeFrom) {
    this.operateTimeFrom = operateTimeFrom;
  }

  public Date getOperateTimeTo() {
    return operateTimeTo;
  }

  public void setOperateTimeTo(Date operateTimeTo) {
    this.operateTimeTo = operateTimeTo;
  }

}
