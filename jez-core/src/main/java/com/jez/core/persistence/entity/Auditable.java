package com.jez.core.persistence.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by JEZ on 2017/5/10.
 */
public interface Auditable<ID extends Serializable, U> extends Persistable<ID> {

  Date getCreatedDate();

  void setCreatedDate(Date createdDate);

  U getCreatedBy();

  void setCreatedBy(U createdBy);

  Date getLastModifiedDate();

  void setLastModifiedDate(Date lastModifiedDate);

  U getLastModifiedBy();

  void setLastModifiedBy(U lastModifiedBy);


}
