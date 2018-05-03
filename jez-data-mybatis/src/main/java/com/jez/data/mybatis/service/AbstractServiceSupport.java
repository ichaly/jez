package com.jez.data.mybatis.service;

import com.jez.core.persistence.entity.Persistable;
import com.jez.data.mybatis.mapper.CrudMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/6/19.
 */
@Transactional(readOnly = true)
public abstract class AbstractServiceSupport<T extends Persistable<Long>, Q, E, M extends CrudMapper<Long, T, E>> extends
    AbstractService<Long, T, Q, E, M> {

}
