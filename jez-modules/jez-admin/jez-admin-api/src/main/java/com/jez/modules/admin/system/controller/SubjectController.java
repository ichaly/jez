package com.jez.modules.admin.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jez.core.ServiceError;
import com.jez.core.validation.groups.Update;
import com.jez.modules.admin.system.dto.Subject;
import com.jez.modules.admin.system.dto.SubjectUserDto;
import com.jez.modules.admin.system.entity.Group;
import com.jez.modules.admin.system.entity.User;
import com.jez.modules.admin.system.security.annotation.UserId;
import com.jez.modules.admin.system.service.GroupService;
import com.jez.modules.admin.system.service.ResourceService;
import com.jez.modules.admin.system.service.UserService;

/**
 * Created by JEZ on 2017/5/31.
 */
@RestController
@RequestMapping("/subjects")
public class SubjectController {

  @Resource
  private UserService userService;

  @Resource
  private PasswordEncoder passwordEncoder;

  @Resource
  private ResourceService resourceService;

  @Resource
  private GroupService groupService;

  @GetMapping
  public Subject getSubject(@UserId Long userId) throws Exception {
    Subject subject = new Subject();
    subject.setUser(userService.getCache(userId));
    subject.setResources(resourceService.findByUserId(userId));
    subject.setGroups(groupService.findByUserId(userId));
    return subject;
  }

  @GetMapping("/user")
  public User getUser(@UserId Long userId) throws Exception {
    return userService.get(userId);
  }

  @GetMapping("/groups")
  public List<Group> getGroups(@UserId Long userId) throws Exception {
    return groupService.findByUserId(userId);
  }

  @GetMapping("/resources")
  public List<com.jez.modules.admin.system.entity.Resource> getResources(@UserId Long userId)
      throws Exception {
    return resourceService.findByUserId(userId);
  }

  @PutMapping("/user")
  public void updateUser(@Validated(Update.class) @RequestBody SubjectUserDto user,
      @UserId Long userId) throws Exception {
    user.setId(userId);
    user.setOfficeId(null);
    if (user.isUpdatePassword()) {
      User currentUser = userService.get(userId);
      if (!passwordEncoder.matches(user.getOldPassword(), currentUser.getPassword())) {
        ServiceError.BAD_CREDENTIALS.raise();
      }
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    } else {
      user.setPassword(null);
    }
    userService.update(user);
  }

}
