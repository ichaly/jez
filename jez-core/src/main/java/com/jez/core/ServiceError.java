package com.jez.core;

/**
 * Created by JEZ on 2017/5/10.
 */
public enum ServiceError {
  DATA_ACCESS, ENTITY_NOT_FOUND, MULTIPLE_RESULTS, ENTITY_EXISTS, DUPLICATE_ROOT, MISSING_ROOT, UNLIMITED_LOOP, SHIFTING_BOUNDARY, ADMINISTRATOR_REQUIRED, BAD_CREDENTIALS;

  public void raise() throws ServiceException {
    throw new ServiceException(this.name());
  }
}
