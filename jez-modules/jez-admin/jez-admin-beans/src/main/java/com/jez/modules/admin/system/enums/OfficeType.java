package com.jez.modules.admin.system.enums;

/**
 * Created by JEZ on 2017/5/15.
 */
public enum OfficeType {

  COMPANY(0), DEPT(1), GROUP(2), OTHER(3);

  private int value;

  OfficeType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
