package com.jez.modules.admin.system.controller;

import com.jez.core.validation.groups.Create;
import com.jez.core.validation.groups.Update;
import com.jez.modules.admin.system.entity.Dict;
import com.jez.modules.admin.system.service.DictService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JEZ on 2017/6/12.
 */
@RestController
@RequestMapping("/system/dicts")
public class DictController {

  @Resource
  private DictService dictService;

  @PreAuthorize("hasAuthority('system:dicts:query')")
  @GetMapping
  public Page<Dict> findPage(@ModelAttribute Dict dict, Pageable pageable)
      throws Exception {
    return dictService.findPage(dict, pageable);
  }

  @PreAuthorize("hasAuthority('system:dicts:query')")
  @GetMapping("/{id}")
  public Dict get(@PathVariable Long id) throws Exception {
    return dictService.get(id);
  }

  @PreAuthorize("hasAuthority('system:dicts:create')")
  @PostMapping
  public void create(@Validated(Create.class) @RequestBody Dict dict) throws Exception {
    dictService.create(dict);
  }

  @PreAuthorize("hasAuthority('system:dicts:update')")
  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @Validated(Update.class) @RequestBody Dict dict)
      throws Exception {
    dict.setId(id);
    dictService.update(dict);
  }

  @PreAuthorize("hasAuthority('system:dicts:delete')")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws Exception {
    dictService.delete(id);
  }

  @PreAuthorize("hasAnyAuthority('system:dicts:create', 'system:dicts:update')")
  @GetMapping("/uniqueness")
  public boolean isUnique(Long id, @RequestParam String type, @RequestParam String value)
      throws Exception {
    return dictService.isUnique(id, type, value);
  }

  @GetMapping("/types")
  public Map<String, List<Dict>> findByTypeIn(@RequestParam String[] types) throws Exception {
    Map<String, List<Dict>> map = new HashMap<>();
    for (String type : types) {
      map.put(type, dictService.findByType(type));
    }
    return map;
  }

}
