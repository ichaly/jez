package com.jez.core.persistence.enums;

/**
 * Created by JEZ on 2017/5/16.
 */
public enum Sex {

  MALE(0), FEMALE(1);

  private int value;

  Sex(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
