package com.jez.modules.admin.system.entity;

import com.jez.core.persistence.entity.Auditable;
import com.jez.core.persistence.entity.NestedSet;
import java.util.Date;

public class Office implements Auditable<Long, Long>, NestedSet<Long> {

  private static final long serialVersionUID = 2890691595491302400L;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.id
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long id;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.created_by
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long createdBy;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.created_date
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Date createdDate;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.last_modified_by
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long lastModifiedBy;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.last_modified_date
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Date lastModifiedDate;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.parent_id
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long parentId;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.lft
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long lft;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.rt
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long rt;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.level
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Integer level;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.type
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Integer type;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.name
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.code
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private String code;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.short_name
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private String shortName;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.functions
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private String functions;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.address
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private String address;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.address_detail
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private String addressDetail;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.zip_code
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private String zipCode;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.telephone
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private String telephone;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.email
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private String email;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.fax
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private String fax;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.leader_id
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long leaderId;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_office.contact_person_id
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long contactPersonId;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.id
   *
   * @return the value of sys_office.id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.id
   *
   * @param id the value for sys_office.id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.created_by
   *
   * @return the value of sys_office.created_by
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getCreatedBy() {
    return createdBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.created_by
   *
   * @param createdBy the value for sys_office.created_by
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.created_date
   *
   * @return the value of sys_office.created_date
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.created_date
   *
   * @param createdDate the value for sys_office.created_date
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.last_modified_by
   *
   * @return the value of sys_office.last_modified_by
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getLastModifiedBy() {
    return lastModifiedBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.last_modified_by
   *
   * @param lastModifiedBy the value for sys_office.last_modified_by
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setLastModifiedBy(Long lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.last_modified_date
   *
   * @return the value of sys_office.last_modified_date
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.last_modified_date
   *
   * @param lastModifiedDate the value for sys_office.last_modified_date
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.parent_id
   *
   * @return the value of sys_office.parent_id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getParentId() {
    return parentId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.parent_id
   *
   * @param parentId the value for sys_office.parent_id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.lft
   *
   * @return the value of sys_office.lft
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getLft() {
    return lft;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.lft
   *
   * @param lft the value for sys_office.lft
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setLft(Long lft) {
    this.lft = lft;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.rt
   *
   * @return the value of sys_office.rt
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getRt() {
    return rt;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.rt
   *
   * @param rt the value for sys_office.rt
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setRt(Long rt) {
    this.rt = rt;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.level
   *
   * @return the value of sys_office.level
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Integer getLevel() {
    return level;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.level
   *
   * @param level the value for sys_office.level
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setLevel(Integer level) {
    this.level = level;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.type
   *
   * @return the value of sys_office.type
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Integer getType() {
    return type;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.type
   *
   * @param type the value for sys_office.type
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setType(Integer type) {
    this.type = type;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.name
   *
   * @return the value of sys_office.name
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.name
   *
   * @param name the value for sys_office.name
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.code
   *
   * @return the value of sys_office.code
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getCode() {
    return code;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.code
   *
   * @param code the value for sys_office.code
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setCode(String code) {
    this.code = code == null ? null : code.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.short_name
   *
   * @return the value of sys_office.short_name
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getShortName() {
    return shortName;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.short_name
   *
   * @param shortName the value for sys_office.short_name
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setShortName(String shortName) {
    this.shortName = shortName == null ? null : shortName.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.functions
   *
   * @return the value of sys_office.functions
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getFunctions() {
    return functions;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.functions
   *
   * @param functions the value for sys_office.functions
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setFunctions(String functions) {
    this.functions = functions == null ? null : functions.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.address
   *
   * @return the value of sys_office.address
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getAddress() {
    return address;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.address
   *
   * @param address the value for sys_office.address
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.address_detail
   *
   * @return the value of sys_office.address_detail
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getAddressDetail() {
    return addressDetail;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.address_detail
   *
   * @param addressDetail the value for sys_office.address_detail
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setAddressDetail(String addressDetail) {
    this.addressDetail = addressDetail == null ? null : addressDetail.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.zip_code
   *
   * @return the value of sys_office.zip_code
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.zip_code
   *
   * @param zipCode the value for sys_office.zip_code
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode == null ? null : zipCode.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.telephone
   *
   * @return the value of sys_office.telephone
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getTelephone() {
    return telephone;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.telephone
   *
   * @param telephone the value for sys_office.telephone
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.email
   *
   * @return the value of sys_office.email
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getEmail() {
    return email;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.email
   *
   * @param email the value for sys_office.email
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.fax
   *
   * @return the value of sys_office.fax
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getFax() {
    return fax;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.fax
   *
   * @param fax the value for sys_office.fax
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setFax(String fax) {
    this.fax = fax == null ? null : fax.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.leader_id
   *
   * @return the value of sys_office.leader_id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getLeaderId() {
    return leaderId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.leader_id
   *
   * @param leaderId the value for sys_office.leader_id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setLeaderId(Long leaderId) {
    this.leaderId = leaderId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_office.contact_person_id
   *
   * @return the value of sys_office.contact_person_id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getContactPersonId() {
    return contactPersonId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_office.contact_person_id
   *
   * @param contactPersonId the value for sys_office.contact_person_id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setContactPersonId(Long contactPersonId) {
    this.contactPersonId = contactPersonId;
  }
}