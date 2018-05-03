package com.jez.modules.admin.system.mapper;

import com.jez.data.mybatis.mapper.CrudMapper;
import com.jez.modules.admin.system.entity.HistoryUser;
import com.jez.modules.admin.system.example.HistoryUserExample;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface HistoryUserMapper extends CrudMapper<Long, HistoryUser, HistoryUserExample> {

}