package com.jez.modules.admin.system.controller;

import com.jez.core.validation.groups.Create;
import com.jez.core.validation.groups.Update;
import com.jez.modules.admin.system.dto.GroupDto;
import com.jez.modules.admin.system.entity.Group;
import com.jez.modules.admin.system.service.GroupService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JEZ on 2017/7/10.
 */
@RestController
@RequestMapping("/system/groups")
public class GroupController {


  @Resource
  private GroupService groupService;

  @PreAuthorize("hasAuthority('system:groups:query')")
  @GetMapping
  public Page<Group> findPage(@ModelAttribute Group group, Pageable pageable) throws Exception {
    return groupService.findPage(group, pageable);
  }

  @GetMapping(params = "all")
  public List<Group> findAll() throws Exception {
    return groupService.findAll();
  }

  @PreAuthorize("hasAuthority('system:groups:query')")
  @GetMapping("/{id}")
  public Group get(@PathVariable Long id) throws Exception {
    return groupService.get(id);
  }

  @PreAuthorize("hasAuthority('system:groups:create')")
  @PostMapping
  public void create(@Validated(Create.class) @RequestBody GroupDto group) throws Exception {
    if (group.isUpdateResources()) {
      groupService.create(group, group.getResourceIds());
    } else {
      groupService.create(group);
    }
  }

  @PreAuthorize("hasAuthority('system:groups:update')")
  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @Validated(Update.class) @RequestBody GroupDto group)
      throws Exception {
    group.setId(id);
    if (group.isUpdateResources()) {
      groupService.update(group, group.getResourceIds());
    } else {
      groupService.update(group);
    }
  }

  @PreAuthorize("hasAuthority('system:groups:delete')")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws Exception {
    groupService.delete(id);
  }

  @PreAuthorize("hasAnyAuthority('system:groups:create', 'system:groups:update')")
  @GetMapping("/uniqueness")
  public boolean isUnique(Long id, @RequestParam String code) throws Exception {
    return groupService.isUnique(id, code);
  }

}
