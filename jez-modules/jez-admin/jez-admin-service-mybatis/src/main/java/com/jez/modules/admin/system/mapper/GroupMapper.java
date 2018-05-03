package com.jez.modules.admin.system.mapper;

import com.jez.data.mybatis.mapper.CrudMapper;
import com.jez.modules.admin.system.entity.Group;
import com.jez.modules.admin.system.example.GroupExample;
import java.util.List;
import java.util.Set;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface GroupMapper extends CrudMapper<Long, Group, GroupExample> {

  List<Group> selectByUserId(Long userId);

  Set<String> selectCodeByUserId(Long userId);

}