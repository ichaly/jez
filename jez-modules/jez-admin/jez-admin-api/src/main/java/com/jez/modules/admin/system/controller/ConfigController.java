package com.jez.modules.admin.system.controller;

import com.jez.modules.admin.system.entity.Config;
import com.jez.modules.admin.system.service.ConfigService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JEZ on 2017/12/1.
 */
@RestController
@RequestMapping("/system/configs")
public class ConfigController {

  @Resource
  private ConfigService configService;

  @GetMapping("/{code}")
  public Config get(@PathVariable String code) throws Exception {
    return configService.get(code);
  }

  @GetMapping(value = "/{code}", params = "multiple=true")
  public List<Config> findByCode(@PathVariable String code) throws Exception {
    return configService.findByCode(code);
  }

}
