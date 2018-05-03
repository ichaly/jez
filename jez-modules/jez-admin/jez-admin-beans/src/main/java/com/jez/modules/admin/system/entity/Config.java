package com.jez.modules.admin.system.entity;

import com.jez.core.persistence.entity.Auditable;
import com.jez.core.persistence.enums.EnableStatus;
import com.jez.core.validation.constraints.Enum;
import com.jez.core.validation.groups.Create;
import com.jez.modules.admin.system.enums.ConfigType;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class Config implements Auditable<Long, Long> {

  /**
   *
   */
  private static final long serialVersionUID = -7169755966067427940L;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.id
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  private Long id;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.created_by
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  private Long createdBy;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.created_date
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  private Date createdDate;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.last_modified_by
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  private Long lastModifiedBy;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.last_modified_date
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  private Date lastModifiedDate;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.type
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  @NotNull(groups = Create.class)
  @Enum(javaType = ConfigType.class, method = "getValue")
  private Integer type;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.name
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  @NotNull(groups = Create.class)
  @Length(min = 1, max = 100)
  private String name;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.code
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  @NotNull(groups = Create.class)
  @Length(min = 1, max = 100)
  private String code;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.sort
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  private Long sort;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.remarks
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  @Length(max = 255)
  private String remarks;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.status
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  @NotNull(groups = Create.class)
  @Enum(javaType = EnableStatus.class, method = "getValue")
  private Integer status;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_config.value
   *
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  @NotNull(groups = Create.class)
  private String value;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.id
   *
   * @return the value of sys_config.id
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public Long getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.id
   *
   * @param id the value for sys_config.id
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.created_by
   *
   * @return the value of sys_config.created_by
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public Long getCreatedBy() {
    return createdBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.created_by
   *
   * @param createdBy the value for sys_config.created_by
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.created_date
   *
   * @return the value of sys_config.created_date
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.created_date
   *
   * @param createdDate the value for sys_config.created_date
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.last_modified_by
   *
   * @return the value of sys_config.last_modified_by
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public Long getLastModifiedBy() {
    return lastModifiedBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.last_modified_by
   *
   * @param lastModifiedBy the value for sys_config.last_modified_by
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setLastModifiedBy(Long lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.last_modified_date
   *
   * @return the value of sys_config.last_modified_date
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.last_modified_date
   *
   * @param lastModifiedDate the value for sys_config.last_modified_date
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.type
   *
   * @return the value of sys_config.type
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public Integer getType() {
    return type;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.type
   *
   * @param type the value for sys_config.type
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setType(Integer type) {
    this.type = type;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.name
   *
   * @return the value of sys_config.name
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.name
   *
   * @param name the value for sys_config.name
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.code
   *
   * @return the value of sys_config.code
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public String getCode() {
    return code;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.code
   *
   * @param code the value for sys_config.code
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setCode(String code) {
    this.code = code == null ? null : code.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.sort
   *
   * @return the value of sys_config.sort
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public Long getSort() {
    return sort;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.sort
   *
   * @param sort the value for sys_config.sort
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setSort(Long sort) {
    this.sort = sort;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.remarks
   *
   * @return the value of sys_config.remarks
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public String getRemarks() {
    return remarks;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.remarks
   *
   * @param remarks the value for sys_config.remarks
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setRemarks(String remarks) {
    this.remarks = remarks == null ? null : remarks.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.status
   *
   * @return the value of sys_config.status
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.status
   *
   * @param status the value for sys_config.status
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setStatus(Integer status) {
    this.status = status;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_config.value
   *
   * @return the value of sys_config.value
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public String getValue() {
    return value;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_config.value
   *
   * @param value the value for sys_config.value
   * @mbg.generated Sat Dec 02 12:04:10 CST 2017
   */
  public void setValue(String value) {
    this.value = value == null ? null : value.trim();
  }
}