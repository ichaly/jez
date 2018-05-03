package com.jez.modules.admin.system.query;

import com.jez.modules.admin.system.entity.LoginLog;
import java.util.Date;

/**
 * Created by JEZ on 2017/10/23.
 */
public class LoginLogQuery extends LoginLog {

  private static final long serialVersionUID = 5617004123831584934L;

  private Date loginTimeFrom;

  private Date loginTimeTo;

  public Date getLoginTimeFrom() {
    return loginTimeFrom;
  }

  public void setLoginTimeFrom(Date loginTimeFrom) {
    this.loginTimeFrom = loginTimeFrom;
  }

  public Date getLoginTimeTo() {
    return loginTimeTo;
  }

  public void setLoginTimeTo(Date loginTimeTo) {
    this.loginTimeTo = loginTimeTo;
  }

}
