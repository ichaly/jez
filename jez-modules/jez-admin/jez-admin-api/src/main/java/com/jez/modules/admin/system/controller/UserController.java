package com.jez.modules.admin.system.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.jez.core.validation.groups.Create;
import com.jez.core.validation.groups.Update;
import com.jez.modules.admin.system.dto.UserDto;
import com.jez.modules.admin.system.entity.User;
import com.jez.modules.admin.system.security.authentication.SessionKickOutManager;
import com.jez.modules.admin.system.service.UserService;

/**
 * Created by JEZ on 2017/5/28.
 */
@RestController
@RequestMapping("/system/users")
public class UserController {

  @Resource
  private UserService userService;

  @Resource
  private PasswordEncoder passwordEncoder;

  @Resource
  private SessionKickOutManager sessionKickOutManager;

  @PreAuthorize("hasAuthority('system:users:query')")
  @GetMapping
  public Page<User> findPage(@ModelAttribute User user, Pageable pageable) throws Exception {
    return userService.findPage(user, pageable);
  }

  @PreAuthorize("hasAuthority('system:users:query')")
  @GetMapping("/{id}")
  public User get(@PathVariable Long id) throws Exception {
    return userService.get(id);
  }

  @PreAuthorize("hasAuthority('system:users:create')")
  @PostMapping
  public void create(@Validated(Create.class) @RequestBody UserDto user) throws Exception {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    if (user.isUpdateGroups()) {
      userService.create(user, user.getGroupIds());
    } else {
      userService.create(user);
    }
  }

  @PreAuthorize("hasAuthority('system:users:update')")
  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @Validated(Update.class) @RequestBody UserDto user)
      throws Exception {
    user.setId(id);
    if (user.getPassword() != null) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    if (user.isKickOut()) {
      sessionKickOutManager.kickOut(id);
    } else if (user.isUpdateGroups()) {
      userService.update(user, user.getGroupIds());
    } else {
      userService.update(user);
    }
  }

  @PreAuthorize("hasAuthority('system:users:delete')")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws Exception {
    userService.delete(id);
  }

  @PreAuthorize("hasAnyAuthority('system:users:create', 'system:users:update')")
  @GetMapping("/uniqueness")
  public boolean isUnique(Long id, @RequestParam String username) throws Exception {
    return userService.isUnique(id, username);
  }

}
