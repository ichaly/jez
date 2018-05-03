package com.jez.modules.admin.system.controller;

import com.jez.core.validation.groups.Create;
import com.jez.core.validation.groups.Update;
import com.jez.modules.admin.system.dto.OfficeDto;
import com.jez.modules.admin.system.entity.Office;
import com.jez.modules.admin.system.service.OfficeService;
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
 * Created by JEZ on 2017/5/17.
 */
@RestController
@RequestMapping("/system/offices")
public class OfficeController {

  @Resource
  private OfficeService officeService;

  @PreAuthorize("hasAuthority('system:offices:query')")
  @GetMapping
  public Page<Office> findPage(@ModelAttribute Office office, Pageable pageable) throws Exception {
    return officeService.findPage(office, pageable);
  }

  @GetMapping(params = "all")
  public List<Office> findAll() throws Exception {
    return officeService.findAll();
  }


  @PreAuthorize("hasAuthority('system:offices:query')")
  @GetMapping("/{id}")
  public Office get(@PathVariable Long id) throws Exception {
    return officeService.get(id);
  }

  @PreAuthorize("hasAuthority('system:offices:create')")
  @PostMapping
  public void create(@Validated(Create.class) @RequestBody Office office) throws Exception {
    officeService.create(office);
  }


  @PreAuthorize("hasAuthority('system:offices:update')")
  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @Validated(Update.class) @RequestBody OfficeDto office)
      throws Exception {
    if (office.isUpdateOrder()) {
      officeService.shift(id, office.isReverse(), office.isEnd());
    } else {
      office.setId(id);
      officeService.update(office);
    }
  }

  @PreAuthorize("hasAuthority('system:offices:delete')")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws Exception {
    officeService.delete(id);
  }

  @PreAuthorize("hasAnyAuthority('system:offices:create', 'system:offices:update')")
  @GetMapping("/uniqueness")
  public boolean isUnique(Long id, @RequestParam String code) throws Exception {
    return officeService.isUnique(id, code);
  }

}
