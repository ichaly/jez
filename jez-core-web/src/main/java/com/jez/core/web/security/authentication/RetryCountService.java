package com.jez.core.web.security.authentication;

/**
 * Created by JEZ on 2017/5/21.
 */
public interface RetryCountService {

  int getByUsername(String username);

  void increaseByUsername(String username);

  void deleteByUsername(String username);

}
