package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.Office;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by JEZ on 2017/5/16.
 */
public interface OfficeService {

  void create(Office office) throws ServiceException;

  void delete(Long id) throws ServiceException;

  void update(Office office) throws ServiceException;

  Office get(Long id) throws ServiceException;

  Page<Office> findPage(Office office, Pageable pageable) throws ServiceException;

  void shift(Long id, boolean reverse, boolean end) throws ServiceException;

  List<Office> findAll() throws ServiceException;

  boolean isUnique(Long id, String code) throws ServiceException;

}
