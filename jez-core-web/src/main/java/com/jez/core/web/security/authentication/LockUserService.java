package com.jez.core.web.security.authentication;

/**
 * Created by JEZ on 2017/5/22.
 */
public interface LockUserService {

  boolean lockByUsername(String username);

}
