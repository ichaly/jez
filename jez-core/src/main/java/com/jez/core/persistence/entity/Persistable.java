package com.jez.core.persistence.entity;

import java.io.Serializable;

/**
 * Created by JEZ on 2017/5/8.
 */
public interface Persistable<ID extends Serializable> extends Serializable {

  ID getId();

  void setId(ID id);

}
