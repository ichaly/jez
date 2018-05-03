package com.jez.modules.admin.system.enums;

/**
 * Created by JEZ on 2017/12/1.
 */
public enum ConfigType {

  SYSTEM(0), CUSTOM(1);

  private int value;

  ConfigType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
