package com.jez.modules.admin.system.mapper;

import com.jez.data.mybatis.mapper.CrudMapper;
import com.jez.modules.admin.system.entity.Dict;
import com.jez.modules.admin.system.example.DictExample;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface DictMapper extends CrudMapper<Long, Dict, DictExample> {

}