package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by JEZ on 2017/6/4.
 */
public interface ResourceService {

  List<Resource> findByUserId(Long userId) throws ServiceException;

  Page<Resource> findPage(Resource resource, Pageable pageable) throws ServiceException;

  Set<String> findPermissionByUserId(Long userId) throws ServiceException;

  void create(Resource resource) throws ServiceException;

  Resource get(Long id) throws ServiceException;

  void update(Resource resource) throws ServiceException;

  void delete(Long id) throws ServiceException;

  void shift(Long id, boolean reverse, boolean end) throws ServiceException;

  List<Resource> findAll() throws ServiceException;

  Map<String, String> getTitleMap() throws ServiceException;

  String toTitleMapKey(String pathExp, String method) throws ServiceException;

}
