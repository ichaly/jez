package com.jez.modules.admin.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jez.core.persistence.entity.Auditable;
import com.jez.core.persistence.enums.BloodType;
import com.jez.core.persistence.enums.EnableStatus;
import com.jez.core.persistence.enums.Sex;
import com.jez.core.validation.constraints.Enum;
import com.jez.core.validation.groups.Create;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User implements Auditable<Long, Long> {

  private static final long serialVersionUID = 6675228315358189984L;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.id
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long id;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.created_by
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long createdBy;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.created_date
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Date createdDate;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.last_modified_by
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Long lastModifiedBy;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.last_modified_date
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  private Date lastModifiedDate;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.office_id
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @NotNull(groups = Create.class)
  private Long officeId;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.username
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @NotEmpty(groups = Create.class)
  @Pattern(regexp = "^[a-zA-z][a-zA-Z0-9_]{3,19}$")
  private String username;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.password
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @JsonIgnore
  @NotEmpty(groups = Create.class)
  @Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$")
  private String password;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.status
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @NotNull(groups = Create.class)
  @Enum(javaType = EnableStatus.class, method = "getValue")
  private Integer status;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.nickname
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @NotEmpty(groups = Create.class)
  @Length(max = 100)
  private String nickname;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.employee_number
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 100)
  private String employeeNumber;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.real_name
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @NotEmpty(groups = Create.class)
  @Length(max = 100)
  private String realName;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.id_card
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 100)
  private String idCard;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.sex
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Enum(javaType = Sex.class, method = "getValue")
  private Integer sex;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.birthday
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date birthday;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.telephone
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 100)
  private String telephone;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.cellphone
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 100)
  private String cellphone;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.email
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Email
  @Length(max = 320)
  private String email;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.address
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 100)
  private String address;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.address_detail
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 255)
  private String addressDetail;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.zip_code
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 100)
  private String zipCode;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.native_address
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 100)
  private String nativeAddress;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.blood_type
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Enum(javaType = BloodType.class, method = "getValue")
  private Integer bloodType;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.school
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 100)
  private String school;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.degree
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 100)
  private String degree;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * sys_user.major
   *
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  @Length(max = 100)
  private String major;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.id
   *
   * @return the value of sys_user.id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.id
   *
   * @param id the value for sys_user.id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.created_by
   *
   * @return the value of sys_user.created_by
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getCreatedBy() {
    return createdBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.created_by
   *
   * @param createdBy the value for sys_user.created_by
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.created_date
   *
   * @return the value of sys_user.created_date
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.created_date
   *
   * @param createdDate the value for sys_user.created_date
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.last_modified_by
   *
   * @return the value of sys_user.last_modified_by
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getLastModifiedBy() {
    return lastModifiedBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.last_modified_by
   *
   * @param lastModifiedBy the value for sys_user.last_modified_by
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setLastModifiedBy(Long lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.last_modified_date
   *
   * @return the value of sys_user.last_modified_date
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.last_modified_date
   *
   * @param lastModifiedDate the value for sys_user.last_modified_date
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.office_id
   *
   * @return the value of sys_user.office_id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Long getOfficeId() {
    return officeId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.office_id
   *
   * @param officeId the value for sys_user.office_id
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.username
   *
   * @return the value of sys_user.username
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getUsername() {
    return username;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.username
   *
   * @param username the value for sys_user.username
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setUsername(String username) {
    this.username = username == null ? null : username.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.password
   *
   * @return the value of sys_user.password
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getPassword() {
    return password;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.password
   *
   * @param password the value for sys_user.password
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setPassword(String password) {
    this.password = password == null ? null : password.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.status
   *
   * @return the value of sys_user.status
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.status
   *
   * @param status the value for sys_user.status
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setStatus(Integer status) {
    this.status = status;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.nickname
   *
   * @return the value of sys_user.nickname
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.nickname
   *
   * @param nickname the value for sys_user.nickname
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setNickname(String nickname) {
    this.nickname = nickname == null ? null : nickname.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.employee_number
   *
   * @return the value of sys_user.employee_number
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getEmployeeNumber() {
    return employeeNumber;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.employee_number
   *
   * @param employeeNumber the value for sys_user.employee_number
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setEmployeeNumber(String employeeNumber) {
    this.employeeNumber = employeeNumber == null ? null : employeeNumber.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.real_name
   *
   * @return the value of sys_user.real_name
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getRealName() {
    return realName;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.real_name
   *
   * @param realName the value for sys_user.real_name
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setRealName(String realName) {
    this.realName = realName == null ? null : realName.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.id_card
   *
   * @return the value of sys_user.id_card
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getIdCard() {
    return idCard;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.id_card
   *
   * @param idCard the value for sys_user.id_card
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setIdCard(String idCard) {
    this.idCard = idCard == null ? null : idCard.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.sex
   *
   * @return the value of sys_user.sex
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Integer getSex() {
    return sex;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.sex
   *
   * @param sex the value for sys_user.sex
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setSex(Integer sex) {
    this.sex = sex;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.birthday
   *
   * @return the value of sys_user.birthday
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Date getBirthday() {
    return birthday;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.birthday
   *
   * @param birthday the value for sys_user.birthday
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.telephone
   *
   * @return the value of sys_user.telephone
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getTelephone() {
    return telephone;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.telephone
   *
   * @param telephone the value for sys_user.telephone
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.cellphone
   *
   * @return the value of sys_user.cellphone
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getCellphone() {
    return cellphone;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.cellphone
   *
   * @param cellphone the value for sys_user.cellphone
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setCellphone(String cellphone) {
    this.cellphone = cellphone == null ? null : cellphone.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.email
   *
   * @return the value of sys_user.email
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getEmail() {
    return email;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.email
   *
   * @param email the value for sys_user.email
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.address
   *
   * @return the value of sys_user.address
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getAddress() {
    return address;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.address
   *
   * @param address the value for sys_user.address
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.address_detail
   *
   * @return the value of sys_user.address_detail
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getAddressDetail() {
    return addressDetail;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.address_detail
   *
   * @param addressDetail the value for sys_user.address_detail
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setAddressDetail(String addressDetail) {
    this.addressDetail = addressDetail == null ? null : addressDetail.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.zip_code
   *
   * @return the value of sys_user.zip_code
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.zip_code
   *
   * @param zipCode the value for sys_user.zip_code
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode == null ? null : zipCode.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.native_address
   *
   * @return the value of sys_user.native_address
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getNativeAddress() {
    return nativeAddress;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.native_address
   *
   * @param nativeAddress the value for sys_user.native_address
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setNativeAddress(String nativeAddress) {
    this.nativeAddress = nativeAddress == null ? null : nativeAddress.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.blood_type
   *
   * @return the value of sys_user.blood_type
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public Integer getBloodType() {
    return bloodType;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.blood_type
   *
   * @param bloodType the value for sys_user.blood_type
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setBloodType(Integer bloodType) {
    this.bloodType = bloodType;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.school
   *
   * @return the value of sys_user.school
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getSchool() {
    return school;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.school
   *
   * @param school the value for sys_user.school
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setSchool(String school) {
    this.school = school == null ? null : school.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.degree
   *
   * @return the value of sys_user.degree
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getDegree() {
    return degree;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.degree
   *
   * @param degree the value for sys_user.degree
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setDegree(String degree) {
    this.degree = degree == null ? null : degree.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column sys_user.major
   *
   * @return the value of sys_user.major
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public String getMajor() {
    return major;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column sys_user.major
   *
   * @param major the value for sys_user.major
   * @mbg.generated Mon Jun 19 16:26:28 CST 2017
   */
  public void setMajor(String major) {
    this.major = major == null ? null : major.trim();
  }

  private String officeName;

  public String getOfficeName() {
    return officeName;
  }

  public void setOfficeName(String officeName) {
    this.officeName = officeName;
  }

}