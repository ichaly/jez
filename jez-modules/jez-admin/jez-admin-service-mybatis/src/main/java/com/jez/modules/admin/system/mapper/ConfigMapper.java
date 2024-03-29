package com.jez.modules.admin.system.mapper;

import com.jez.data.mybatis.mapper.CrudMapper;
import com.jez.modules.admin.system.entity.Config;
import com.jez.modules.admin.system.example.ConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigMapper extends CrudMapper<Long, Config, ConfigExample> {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  List<Config> selectByExampleWithBLOBs(ConfigExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  int updateByExampleWithBLOBs(@Param("record") Config record,
      @Param("example") ConfigExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  int updateByPrimaryKeyWithBLOBs(Config record);

}