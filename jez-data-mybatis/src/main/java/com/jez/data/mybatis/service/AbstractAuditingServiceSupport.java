package com.jez.data.mybatis.service;

import com.jez.core.persistence.entity.Auditable;
import com.jez.data.mybatis.mapper.CrudMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/6/19.
 */
@Transactional(readOnly = true)
public abstract class AbstractAuditingServiceSupport<T extends Auditable<Long, Long>, Q, E, M extends CrudMapper<Long, T, E>> extends
    AbstractAuditingService<Long, Long, T, Q, E, M> {

}
