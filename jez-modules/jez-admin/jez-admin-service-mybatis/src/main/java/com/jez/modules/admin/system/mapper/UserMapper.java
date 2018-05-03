package com.jez.modules.admin.system.mapper;

import com.jez.data.mybatis.mapper.CrudMapper;
import com.jez.modules.admin.system.entity.User;
import com.jez.modules.admin.system.example.UserExample;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface UserMapper extends CrudMapper<Long, User, UserExample> {

}