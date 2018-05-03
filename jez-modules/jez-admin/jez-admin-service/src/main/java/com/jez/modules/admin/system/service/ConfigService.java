package com.jez.modules.admin.system.service;

import com.jez.core.ServiceException;
import com.jez.modules.admin.system.entity.Config;
import java.util.List;

/**
 * Created by JEZ on 2017/12/1.
 */
public interface ConfigService {

  Config get(String code) throws ServiceException;

  List<Config> findByCode(String code) throws ServiceException;

}
