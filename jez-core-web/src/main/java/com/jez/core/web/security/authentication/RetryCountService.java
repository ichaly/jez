package com.jez.core.web.security.authentication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by JEZ on 2017/5/21.
 */
public interface RetryCountService {

  AtomicInteger getByUsername(String username);

  void deleteByUsername(String username);

}
