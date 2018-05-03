package com.jez.data.mybatis.mapper;

import com.jez.core.persistence.entity.NestedSet;
import java.io.Serializable;
import org.apache.ibatis.annotations.Param;

/**
 * Created by JEZ on 2017/6/19.
 */
public interface NestedSetMapper<ID extends Serializable, T extends NestedSet<ID>, E> extends
    CrudMapper<ID, T, E> {

  int shiftLft(@Param("from") Long from, @Param("to") Long to, @Param("step") long step);

  int shiftRt(@Param("from") Long from, @Param("to") Long to, @Param("step") long step);

  int shiftLevelBetween(@Param("from") Long from, @Param("to") Long to,
      @Param("levelStep") int levelStep);

}
