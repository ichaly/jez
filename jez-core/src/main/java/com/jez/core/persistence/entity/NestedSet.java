package com.jez.core.persistence.entity;

import java.io.Serializable;

/**
 * Created by JEZ on 2017/5/10.
 */
public interface NestedSet<ID extends Serializable> extends Persistable<ID> {

  long STEP_OF_INCREASE = 2L;
  long LFT_OF_ROOT = 1L;
  long RT_OF_ROOT = 2L;
  int LEVEL_OF_ROOT = 1;

  ID getParentId();

  void setParentId(ID parentID);

  Long getLft();

  void setLft(Long lft);

  Long getRt();

  void setRt(Long rt);

  Integer getLevel();

  void setLevel(Integer level);

}
