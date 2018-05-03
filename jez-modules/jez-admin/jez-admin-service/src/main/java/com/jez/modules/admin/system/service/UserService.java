package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by JEZ on 2017/5/18.
 */
public interface UserService {

  User get(Long id) throws ServiceException;

  User getCache(Long id) throws ServiceException;

  User getByUsername(String username) throws ServiceException;

  void disableByUsername(String username) throws ServiceException;

  Page<User> findPage(User user, Pageable pageable) throws ServiceException;

  void create(User user) throws ServiceException;

  void create(User user, Long[] groupIds) throws ServiceException;

  void update(User user) throws ServiceException;

  void update(User user, Long[] groupIds) throws ServiceException;

  void delete(Long id) throws ServiceException;

  boolean isUnique(Long id, String username) throws ServiceException;
}
