package com.jez.core.web.security.authentication;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.concurrent.ConcurrentMapCache;

/**
 * Created by JEZ on 2017/5/29.
 */
public class CacheRetryCountService implements RetryCountService {

  private Cache cache;

  public CacheRetryCountService() {
    this(null);
  }

  public CacheRetryCountService(Cache cache) {
    if (cache == null) {
      cache = new ConcurrentMapCache(CacheRetryCountService.class.getName());
    }
    this.cache = cache;
  }

  @Override
  public int getByUsername(String username) {
    ValueWrapper valueWrapper = cache.get(username);
    if (valueWrapper == null || valueWrapper.get() == null) {
      int count = 0;
      cache.put(username, count);
      return count;
    }
    return (int) valueWrapper.get();
  }

  @Override
  public void increaseByUsername(String username) {
    cache.put(username, getByUsername(username) + 1);
  }

  @Override
  public void deleteByUsername(String username) {
    cache.put(username, null);
  }

}
