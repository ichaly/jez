package com.jez.data.mybatis.service;

import com.jez.core.persistence.entity.NestedSet;
import com.jez.data.mybatis.mapper.NestedSetMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/6/19.
 */
@Transactional(readOnly = true)
public abstract class AbstractNestedSetServiceSupport<T extends NestedSet<Long>, Q, E, M extends NestedSetMapper<Long, T, E>> extends
    AbstractNestedSetService<Long, T, Q, E, M> {

}
