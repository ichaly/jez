package com.jez.data.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jez.core.ServiceError;
import com.jez.core.ServiceException;
import com.jez.data.mybatis.mapper.CrudMapper;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * Created by JEZ on 2017/6/19.
 */
@Transactional
public abstract class AbstractService<ID extends Serializable, T, Q, E, M extends CrudMapper<ID, T, E>> {

  private static final String LIKE_PARAMETER_FORMATTER = "%%%s%%";
  private static final String STARTS_WITH_PARAMETER_FORMATTER = "%s%%";
  private static final String ENDS_WITH_PARAMETER_FORMATTER = "%%%s";

  @Autowired
  protected M mapper;

  @Transactional
  public void create(T entity) throws ServiceException {
    preCreate(entity);
    doCreate(entity);
    postCreate(entity);
  }

  protected void preCreate(T entity) throws ServiceException {
    assignIdIfNotAutoIdentity(entity);
  }

  protected void doCreate(T entity) throws ServiceException {
    mapper.insertSelective(entity);
  }

  protected void postCreate(T entity) throws ServiceException {
  }

  protected void assignIdIfNotAutoIdentity(T entity) throws ServiceException {
  }

  @Transactional
  public void delete(ID id) throws ServiceException {
    preDelete(id);
    doDelete(id);
    postDelete(id);
  }

  protected void preDelete(ID id) throws ServiceException {
  }

  protected void doDelete(ID id) throws ServiceException {
    if (mapper.deleteByPrimaryKey(id) == 0) {
      ServiceError.ENTITY_NOT_FOUND.raise();
    }
  }

  protected void postDelete(ID id) throws ServiceException {
  }

  @Transactional
  public void update(T entity) throws ServiceException {
    preUpdate(entity);
    doUpdate(entity);
    postUpdate(entity);
  }

  protected void preUpdate(T entity) throws ServiceException {
  }

  protected void doUpdate(T entity) throws ServiceException {
    if (mapper.updateByPrimaryKeySelective(entity) == 0) {
      ServiceError.ENTITY_NOT_FOUND.raise();
    }
  }

  protected void postUpdate(T entity) throws ServiceException {
  }

  public T get(ID id) throws ServiceException {
    T entity = mapper.selectByPrimaryKey(id);
    if (entity == null) {
      ServiceError.ENTITY_NOT_FOUND.raise();
    }
    return entity;
  }

  public List<T> findList(Q query) throws ServiceException {
    return mapper.selectByExample(convertToExample(query));
  }

  public Page<T> findPage(Q query, Pageable pageable) throws ServiceException {
    startPage(pageable);
    return toPage(new PageInfo<>(findList(query)), pageable);
  }

  protected void startPage(Pageable pageable) throws ServiceException {
    PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
  }

  protected Page<T> toPage(PageInfo<T> pageInfo, Pageable pageable) throws ServiceException {
    return new PageImpl<>(pageInfo.getList(), pageable, pageInfo.getTotal());
  }

  public long count(Q query) throws ServiceException {
    return mapper.countByExample(convertToExample(query));
  }

  protected E convertToExample(Q query) throws ServiceException {
    return null;
  }

  protected String toLikeParameter(String parameter) {
    return String.format(LIKE_PARAMETER_FORMATTER, parameter);
  }

  protected String toStartsWithParameter(String parameter) {
    return String.format(STARTS_WITH_PARAMETER_FORMATTER, parameter);
  }

  protected String toEndsWithParameter(String parameter) {
    return String.format(ENDS_WITH_PARAMETER_FORMATTER, parameter);
  }

  protected T getOne(List<T> list) {
    return CollectionUtils.isEmpty(list) ? null : list.get(0);
  }

}
