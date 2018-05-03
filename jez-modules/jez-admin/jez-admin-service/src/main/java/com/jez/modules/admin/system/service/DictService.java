package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.Dict;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by JEZ on 2017/5/16.
 */
public interface DictService {

  void create(Dict dict) throws ServiceException;

  void delete(Long id) throws ServiceException;

  void update(Dict dict) throws ServiceException;

  Dict get(Long id) throws ServiceException;

  Page<Dict> findPage(Dict dict, Pageable pageable) throws ServiceException;

  List<Dict> findByType(String type) throws ServiceException;

  Dict get(String type, String value) throws ServiceException;

  boolean isUnique(Long id, String type, String value) throws ServiceException;

}
