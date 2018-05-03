package com.jez.modules.admin.system.controller;

import com.jez.core.validation.groups.Create;
import com.jez.core.validation.groups.Update;
import com.jez.modules.admin.system.dto.ResourceDto;
import com.jez.modules.admin.system.service.ResourceService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JEZ on 2017/6/10.
 */
@RestController
@RequestMapping("/system/resources")
public class ResourceController {

  @Resource
  private ResourceService resourceService;

  @PreAuthorize("hasAuthority('system:resources:query')")
  @GetMapping
  public Page<com.jez.modules.admin.system.entity.Resource> findPage(
      @ModelAttribute com.jez.modules.admin.system.entity.Resource resource, Pageable pageable)
      throws Exception {
    return resourceService.findPage(resource, pageable);
  }

  @GetMapping(params = "all")
  public List<com.jez.modules.admin.system.entity.Resource> findAll() throws Exception {
    return resourceService.findAll();
  }


  @PreAuthorize("hasAuthority('system:resources:query')")
  @GetMapping("/{id}")
  public com.jez.modules.admin.system.entity.Resource get(@PathVariable Long id) throws Exception {
    return resourceService.get(id);
  }

  @PreAuthorize("hasAuthority('system:resources:create')")
  @PostMapping
  public void create(
      @Validated(Create.class) @RequestBody com.jez.modules.admin.system.entity.Resource resource)
      throws Exception {
    resourceService.create(resource);
  }


  @PreAuthorize("hasAuthority('system:resources:update')")
  @PutMapping("/{id}")
  public void update(@PathVariable Long id,
      @Validated(Update.class) @RequestBody ResourceDto resource)
      throws Exception {
    if (resource.isUpdateOrder()) {
      resourceService.shift(id, resource.isReverse(), resource.isEnd());
    } else {
      resource.setId(id);
      resourceService.update(resource);
    }
  }

  @PreAuthorize("hasAuthority('system:resources:delete')")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws Exception {
    resourceService.delete(id);
  }

}
