package com.jez.core.persistence.enums;

/**
 * Created by JEZ on 2017/5/16.
 */
public enum EnableStatus {

  ENABLED(0), DISABLED(1);

  private int value;

  EnableStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
