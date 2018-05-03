package com.jez.data.mybatis.service;

import com.jez.core.ServiceException;
import com.jez.core.persistence.entity.Auditable;
import com.jez.data.mybatis.mapper.CrudMapper;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/6/19.
 */
@Transactional(readOnly = true)
public abstract class AbstractAuditingService<ID extends Serializable, U, T extends Auditable<ID, U>, Q, E, M extends CrudMapper<ID, T, E>> extends
    AbstractService<ID, T, Q, E, M> {

  @Autowired
  protected AuditorAware<U> auditorAware;

  @Resource
  protected DateTimeProvider dateTimeProvider;

  @Override
  protected void preCreate(T entity) throws ServiceException {
    super.preCreate(entity);
    Date date = dateTimeProvider.getNow().getTime();
    U by = auditorAware.getCurrentAuditor();
    entity.setCreatedDate(date);
    entity.setCreatedBy(by);
    entity.setLastModifiedDate(date);
    entity.setLastModifiedBy(by);
  }

  @Override
  protected void preUpdate(T entity) throws ServiceException {
    super.preUpdate(entity);
    entity.setCreatedDate(null);
    entity.setCreatedBy(null);
    entity.setLastModifiedDate(dateTimeProvider.getNow().getTime());
    entity.setLastModifiedBy(auditorAware.getCurrentAuditor());
  }
}
