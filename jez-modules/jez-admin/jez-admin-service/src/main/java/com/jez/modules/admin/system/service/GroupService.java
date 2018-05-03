package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.Group;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by JEZ on 2017/6/4.
 */
public interface GroupService {

  List<Group> findByUserId(Long userId) throws ServiceException;

  Set<String> findCodeByUserId(Long userId) throws ServiceException;

  Group get(Long id) throws ServiceException;

  void create(Group group) throws ServiceException;

  void create(Group group, Long[] resourceIds) throws ServiceException;

  void update(Group group) throws ServiceException;

  void update(Group group, Long[] resourceIds) throws ServiceException;

  boolean isUnique(Long id, String code) throws ServiceException;

  Page<Group> findPage(Group group, Pageable pageable) throws ServiceException;

  List<Group> findAll() throws ServiceException;

  void delete(Long id) throws ServiceException;

}
