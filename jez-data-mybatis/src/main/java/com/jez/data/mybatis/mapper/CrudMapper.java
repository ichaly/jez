package com.jez.data.mybatis.mapper;

import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by JEZ on 2017/6/19.
 */
public interface CrudMapper<ID extends Serializable, T, E> {

  long countByExample(E example);

  int deleteByExample(E example);

  int deleteByPrimaryKey(ID id);

  int insert(T record);

  int insertSelective(T record);

  List<T> selectByExample(E example);

  T selectByPrimaryKey(ID id);

  int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

  int updateByExample(@Param("record") T record, @Param("example") E example);

  int updateByPrimaryKeySelective(T record);

  int updateByPrimaryKey(T record);
}
