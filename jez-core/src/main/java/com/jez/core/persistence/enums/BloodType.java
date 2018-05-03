package com.jez.core.persistence.enums;

/**
 * Created by JEZ on 2017/5/16.
 */
public enum BloodType {

  A(0), B(1), AB(2), O(3), OTHER(4);

  private int value;

  BloodType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
