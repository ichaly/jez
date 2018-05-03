package com.jez.modules.admin.system.mapper;

import com.jez.data.mybatis.mapper.NestedSetMapper;
import com.jez.modules.admin.system.entity.Office;
import com.jez.modules.admin.system.example.OfficeExample;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface OfficeMapper extends NestedSetMapper<Long, Office, OfficeExample> {

}