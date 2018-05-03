package com.jez.modules.admin.system.enums;

/**
 * Created by JEZ on 2017/5/15.
 */
public enum ResourceType {

  MENU(0), API(1);

  private int value;

  ResourceType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
