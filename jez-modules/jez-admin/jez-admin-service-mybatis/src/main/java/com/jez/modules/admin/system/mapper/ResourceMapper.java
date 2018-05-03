package com.jez.modules.admin.system.mapper;

import com.jez.data.mybatis.mapper.NestedSetMapper;
import com.jez.modules.admin.system.entity.Resource;
import com.jez.modules.admin.system.example.ResourceExample;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface ResourceMapper extends NestedSetMapper<Long, Resource, ResourceExample> {

  List<Resource> selectByUserIdAndStatus(@Param("userId") Long userId, @Param("status") int status);

  Set<String> selectPermission();

  Set<String> selectPermissionByUserIdAndStatus(@Param("userId") Long userId,
      @Param("status") int status);
}