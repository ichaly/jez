package com.jez.data.mybatis.service;

import com.jez.core.ServiceError;
import com.jez.core.ServiceException;
import com.jez.core.persistence.entity.NestedSet;
import com.jez.data.mybatis.mapper.NestedSetMapper;
import java.io.Serializable;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JEZ on 2017/6/19.
 */
@Transactional(readOnly = true)
public abstract class AbstractNestedSetService<ID extends Serializable, T extends NestedSet<ID>, Q, E, M extends NestedSetMapper<ID, T, E>> extends
    AbstractService<ID, T, Q, E, M> {

  protected abstract E convertToRtEqualToExample(Long rt);

  protected abstract E convertToLftEqualToExample(Long lft);

  protected abstract E convertToBetweenExample(Long lft, Long rt);

  protected long getOccupiedSpace(T entity) throws ServiceException {
    return entity.getRt() - entity.getLft() + 1L;
  }

  @Override
  protected void preCreate(T entity) throws ServiceException {
    super.preCreate(entity);
    if (entity.getParentId() != null) {
      T parent = get(entity.getParentId());
      mapper.shiftLft(parent.getRt(), null, NestedSet.STEP_OF_INCREASE);
      mapper.shiftRt(parent.getRt(), null, NestedSet.STEP_OF_INCREASE);
      entity.setLft(parent.getRt());
      entity.setRt(parent.getRt() + 1L);
      entity.setLevel(parent.getLevel() + 1);
    } else {
      if (count(null) != 0L) {
        ServiceError.DUPLICATE_ROOT.raise();
      }
      entity.setLft(NestedSet.LFT_OF_ROOT);
      entity.setRt(NestedSet.RT_OF_ROOT);
      entity.setLevel(NestedSet.LEVEL_OF_ROOT);
    }
  }

  @Override
  @Transactional
  public void create(T entity) throws ServiceException {
    synchronized (this.getClass()) {
      super.create(entity);
    }
  }

  @Override
  protected void doDelete(ID id) throws ServiceException {
    T entity = get(id);
    preDelete(entity);
    doDelete(entity);
    postDelete(entity);
  }

  protected void preDelete(T entity) throws ServiceException {
  }

  protected void doDelete(T entity) throws ServiceException {
    if (mapper.deleteByExample(convertToBetweenExample(entity.getLft(), entity.getRt())) == 0) {
      ServiceError.ENTITY_NOT_FOUND.raise();
    }
    mapper.shiftLft(entity.getRt(), null, 0L - getOccupiedSpace(entity));
    mapper.shiftRt(entity.getRt(), null, 0L - getOccupiedSpace(entity));
  }

  protected void postDelete(T entity) throws ServiceException {
  }

  @Override
  @Transactional
  public void delete(ID id) throws ServiceException {
    synchronized (this.getClass()) {
      super.delete(id);
    }
  }

  @Override
  protected void preUpdate(T entity) throws ServiceException {
    super.preUpdate(entity);
    T current = get(entity.getId());
    if (entity.getParentId() == null && current.getParentId() != null) {
      ServiceError.DUPLICATE_ROOT.raise();
    }
    if (current.getParentId() == null && entity.getParentId() != null) {
      ServiceError.MISSING_ROOT.raise();
    }
    if ((entity.getParentId() == null && current.getParentId() == null)
        || entity.getParentId() == current.getParentId()) {
      entity.setLft(current.getLft());
      entity.setRt(current.getRt());
      entity.setLevel(current.getLevel());
    } else {
      T parent = get(entity.getParentId());
      if (parent.getLft() >= current.getLft() && parent.getRt() <= current.getRt()) {
        ServiceError.UNLIMITED_LOOP.raise();
      }
      int levelStep = parent.getLevel() + 1 - current.getLevel();
      mapper.shiftLevelBetween(current.getLft(), current.getRt(), levelStep);
      long stepTemp = shiftTemp(current);
      long stepOthers;
      long begin;
      long end;
      if (current.getLft() > parent.getRt()) {
        begin = parent.getRt();
        end = current.getLft() - 1L;
        stepOthers = getOccupiedSpace(current);
      } else {
        begin = current.getRt() + 1L;
        end = parent.getRt() - 1L;
        stepOthers = 0L - getOccupiedSpace(current);
      }
      mapper.shiftLft(begin, end, stepOthers);
      mapper.shiftRt(begin, end, stepOthers);
      long step = parent.getRt() - current.getRt() - 1L;
      long stepWithTemp = step - stepTemp;
      mapper.shiftLft(null, 0L, stepWithTemp);
      mapper.shiftRt(null, 0L, stepWithTemp);
      entity.setLft(current.getLft() + step);
      entity.setRt(current.getRt() + step);
      entity.setLevel(current.getLevel() + levelStep);
    }
  }

  protected long shiftTemp(T entity) throws ServiceException {
    long stepTemp = 0L - entity.getRt();
    mapper.shiftLft(entity.getLft(), entity.getRt(), stepTemp);
    mapper.shiftRt(entity.getLft(), entity.getRt(), stepTemp);
    return stepTemp;
  }

  @Override
  @Transactional
  public void update(T entity) throws ServiceException {
    synchronized (this.getClass()) {
      super.update(entity);
    }
  }

  @Transactional
  public void shift(ID id, boolean reverse, boolean end) throws ServiceException {
    synchronized (this.getClass()) {
      if (end) {
        shiftEnd(id, reverse);
      } else {
        shift(id, reverse);
      }
    }
  }

  protected T getShiftTarget(T entity, boolean reverse) {
    E example = reverse ? convertToRtEqualToExample(entity.getLft() - 1L)
        : convertToLftEqualToExample(entity.getRt() + 1L);
    return getOne(mapper.selectByExample(example));
  }

  protected void shift(ID id, boolean reverse) throws ServiceException {
    T entity = get(id);
    T target = getShiftTarget(entity, reverse);
    if (target == null) {
      ServiceError.SHIFTING_BOUNDARY.raise();
    }
    long stepTemp = shiftTemp(entity);
    long stepOthers = reverse ? getOccupiedSpace(entity) : 0L - getOccupiedSpace(entity);
    mapper.shiftLft(target.getLft(), target.getRt(), stepOthers);
    mapper.shiftRt(target.getLft(), target.getRt(), stepOthers);
    long step = (reverse ? 0L - getOccupiedSpace(target) : getOccupiedSpace(target)) - stepTemp;
    mapper.shiftLft(null, 0L, step);
    mapper.shiftRt(null, 0L, step);
  }

  protected void shiftEnd(ID id, boolean reverse) throws ServiceException {
    T entity = get(id);
    if (entity.getParentId() == null) {
      ServiceError.SHIFTING_BOUNDARY.raise();
    }
    T parent = get(entity.getParentId());
    if ((reverse && parent.getLft() == entity.getLft() - 1L) || (!reverse
        && parent.getRt() == entity.getRt() + 1L)) {
      ServiceError.SHIFTING_BOUNDARY.raise();
    }
    long stepTemp = shiftTemp(entity);
    long stepOthers = reverse ? getOccupiedSpace(entity) : 0L - getOccupiedSpace(entity);
    long begin = reverse ? parent.getLft() + 1L : entity.getRt() + 1L;
    long end = reverse ? entity.getLft() - 1L : parent.getRt() - 1L;
    mapper.shiftLft(begin, end, stepOthers);
    mapper.shiftRt(begin, end, stepOthers);
    long step =
        reverse ? parent.getLft() - entity.getLft() + 1L : parent.getRt() - entity.getRt() - 1L;
    long stepWithTemp = step - stepTemp;
    mapper.shiftLft(null, 0L, stepWithTemp);
    mapper.shiftRt(null, 0L, stepWithTemp);
  }

}
