package com.jez.modules.admin.system.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigExample {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  protected String orderByClause;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  protected boolean distinct;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  protected List<Criteria> oredCriteria;

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public ConfigExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public String getOrderByClause() {
    return orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public boolean isDistinct() {
    return distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  protected abstract static class GeneratedCriteria {

    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
      super();
      criteria = new ArrayList<Criterion>();
    }

    public boolean isValid() {
      return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      return criteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    protected void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
    }

    public Criteria andIdIsNull() {
      addCriterion("c.id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("c.id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Long value) {
      addCriterion("c.id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Long value) {
      addCriterion("c.id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Long value) {
      addCriterion("c.id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Long value) {
      addCriterion("c.id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Long value) {
      addCriterion("c.id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Long value) {
      addCriterion("c.id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Long> values) {
      addCriterion("c.id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Long> values) {
      addCriterion("c.id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Long value1, Long value2) {
      addCriterion("c.id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Long value1, Long value2) {
      addCriterion("c.id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andCreatedByIsNull() {
      addCriterion("c.created_by is null");
      return (Criteria) this;
    }

    public Criteria andCreatedByIsNotNull() {
      addCriterion("c.created_by is not null");
      return (Criteria) this;
    }

    public Criteria andCreatedByEqualTo(Long value) {
      addCriterion("c.created_by =", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByNotEqualTo(Long value) {
      addCriterion("c.created_by <>", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByGreaterThan(Long value) {
      addCriterion("c.created_by >", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByGreaterThanOrEqualTo(Long value) {
      addCriterion("c.created_by >=", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByLessThan(Long value) {
      addCriterion("c.created_by <", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByLessThanOrEqualTo(Long value) {
      addCriterion("c.created_by <=", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByIn(List<Long> values) {
      addCriterion("c.created_by in", values, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByNotIn(List<Long> values) {
      addCriterion("c.created_by not in", values, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByBetween(Long value1, Long value2) {
      addCriterion("c.created_by between", value1, value2, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByNotBetween(Long value1, Long value2) {
      addCriterion("c.created_by not between", value1, value2, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedDateIsNull() {
      addCriterion("c.created_date is null");
      return (Criteria) this;
    }

    public Criteria andCreatedDateIsNotNull() {
      addCriterion("c.created_date is not null");
      return (Criteria) this;
    }

    public Criteria andCreatedDateEqualTo(Date value) {
      addCriterion("c.created_date =", value, "createdDate");
      return (Criteria) this;
    }

    public Criteria andCreatedDateNotEqualTo(Date value) {
      addCriterion("c.created_date <>", value, "createdDate");
      return (Criteria) this;
    }

    public Criteria andCreatedDateGreaterThan(Date value) {
      addCriterion("c.created_date >", value, "createdDate");
      return (Criteria) this;
    }

    public Criteria andCreatedDateGreaterThanOrEqualTo(Date value) {
      addCriterion("c.created_date >=", value, "createdDate");
      return (Criteria) this;
    }

    public Criteria andCreatedDateLessThan(Date value) {
      addCriterion("c.created_date <", value, "createdDate");
      return (Criteria) this;
    }

    public Criteria andCreatedDateLessThanOrEqualTo(Date value) {
      addCriterion("c.created_date <=", value, "createdDate");
      return (Criteria) this;
    }

    public Criteria andCreatedDateIn(List<Date> values) {
      addCriterion("c.created_date in", values, "createdDate");
      return (Criteria) this;
    }

    public Criteria andCreatedDateNotIn(List<Date> values) {
      addCriterion("c.created_date not in", values, "createdDate");
      return (Criteria) this;
    }

    public Criteria andCreatedDateBetween(Date value1, Date value2) {
      addCriterion("c.created_date between", value1, value2, "createdDate");
      return (Criteria) this;
    }

    public Criteria andCreatedDateNotBetween(Date value1, Date value2) {
      addCriterion("c.created_date not between", value1, value2, "createdDate");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByIsNull() {
      addCriterion("c.last_modified_by is null");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByIsNotNull() {
      addCriterion("c.last_modified_by is not null");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByEqualTo(Long value) {
      addCriterion("c.last_modified_by =", value, "lastModifiedBy");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByNotEqualTo(Long value) {
      addCriterion("c.last_modified_by <>", value, "lastModifiedBy");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByGreaterThan(Long value) {
      addCriterion("c.last_modified_by >", value, "lastModifiedBy");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByGreaterThanOrEqualTo(Long value) {
      addCriterion("c.last_modified_by >=", value, "lastModifiedBy");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByLessThan(Long value) {
      addCriterion("c.last_modified_by <", value, "lastModifiedBy");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByLessThanOrEqualTo(Long value) {
      addCriterion("c.last_modified_by <=", value, "lastModifiedBy");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByIn(List<Long> values) {
      addCriterion("c.last_modified_by in", values, "lastModifiedBy");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByNotIn(List<Long> values) {
      addCriterion("c.last_modified_by not in", values, "lastModifiedBy");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByBetween(Long value1, Long value2) {
      addCriterion("c.last_modified_by between", value1, value2, "lastModifiedBy");
      return (Criteria) this;
    }

    public Criteria andLastModifiedByNotBetween(Long value1, Long value2) {
      addCriterion("c.last_modified_by not between", value1, value2, "lastModifiedBy");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateIsNull() {
      addCriterion("c.last_modified_date is null");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateIsNotNull() {
      addCriterion("c.last_modified_date is not null");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateEqualTo(Date value) {
      addCriterion("c.last_modified_date =", value, "lastModifiedDate");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateNotEqualTo(Date value) {
      addCriterion("c.last_modified_date <>", value, "lastModifiedDate");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateGreaterThan(Date value) {
      addCriterion("c.last_modified_date >", value, "lastModifiedDate");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateGreaterThanOrEqualTo(Date value) {
      addCriterion("c.last_modified_date >=", value, "lastModifiedDate");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateLessThan(Date value) {
      addCriterion("c.last_modified_date <", value, "lastModifiedDate");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateLessThanOrEqualTo(Date value) {
      addCriterion("c.last_modified_date <=", value, "lastModifiedDate");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateIn(List<Date> values) {
      addCriterion("c.last_modified_date in", values, "lastModifiedDate");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateNotIn(List<Date> values) {
      addCriterion("c.last_modified_date not in", values, "lastModifiedDate");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateBetween(Date value1, Date value2) {
      addCriterion("c.last_modified_date between", value1, value2, "lastModifiedDate");
      return (Criteria) this;
    }

    public Criteria andLastModifiedDateNotBetween(Date value1, Date value2) {
      addCriterion("c.last_modified_date not between", value1, value2, "lastModifiedDate");
      return (Criteria) this;
    }

    public Criteria andTypeIsNull() {
      addCriterion("c.type is null");
      return (Criteria) this;
    }

    public Criteria andTypeIsNotNull() {
      addCriterion("c.type is not null");
      return (Criteria) this;
    }

    public Criteria andTypeEqualTo(Integer value) {
      addCriterion("c.type =", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotEqualTo(Integer value) {
      addCriterion("c.type <>", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeGreaterThan(Integer value) {
      addCriterion("c.type >", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
      addCriterion("c.type >=", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeLessThan(Integer value) {
      addCriterion("c.type <", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeLessThanOrEqualTo(Integer value) {
      addCriterion("c.type <=", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeIn(List<Integer> values) {
      addCriterion("c.type in", values, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotIn(List<Integer> values) {
      addCriterion("c.type not in", values, "type");
      return (Criteria) this;
    }

    public Criteria andTypeBetween(Integer value1, Integer value2) {
      addCriterion("c.type between", value1, value2, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotBetween(Integer value1, Integer value2) {
      addCriterion("c.type not between", value1, value2, "type");
      return (Criteria) this;
    }

    public Criteria andNameIsNull() {
      addCriterion("c.name is null");
      return (Criteria) this;
    }

    public Criteria andNameIsNotNull() {
      addCriterion("c.name is not null");
      return (Criteria) this;
    }

    public Criteria andNameEqualTo(String value) {
      addCriterion("c.name =", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotEqualTo(String value) {
      addCriterion("c.name <>", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameGreaterThan(String value) {
      addCriterion("c.name >", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameGreaterThanOrEqualTo(String value) {
      addCriterion("c.name >=", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLessThan(String value) {
      addCriterion("c.name <", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLessThanOrEqualTo(String value) {
      addCriterion("c.name <=", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLike(String value) {
      addCriterion("c.name like", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotLike(String value) {
      addCriterion("c.name not like", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameIn(List<String> values) {
      addCriterion("c.name in", values, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotIn(List<String> values) {
      addCriterion("c.name not in", values, "name");
      return (Criteria) this;
    }

    public Criteria andNameBetween(String value1, String value2) {
      addCriterion("c.name between", value1, value2, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotBetween(String value1, String value2) {
      addCriterion("c.name not between", value1, value2, "name");
      return (Criteria) this;
    }

    public Criteria andCodeIsNull() {
      addCriterion("c.code is null");
      return (Criteria) this;
    }

    public Criteria andCodeIsNotNull() {
      addCriterion("c.code is not null");
      return (Criteria) this;
    }

    public Criteria andCodeEqualTo(String value) {
      addCriterion("c.code =", value, "code");
      return (Criteria) this;
    }

    public Criteria andCodeNotEqualTo(String value) {
      addCriterion("c.code <>", value, "code");
      return (Criteria) this;
    }

    public Criteria andCodeGreaterThan(String value) {
      addCriterion("c.code >", value, "code");
      return (Criteria) this;
    }

    public Criteria andCodeGreaterThanOrEqualTo(String value) {
      addCriterion("c.code >=", value, "code");
      return (Criteria) this;
    }

    public Criteria andCodeLessThan(String value) {
      addCriterion("c.code <", value, "code");
      return (Criteria) this;
    }

    public Criteria andCodeLessThanOrEqualTo(String value) {
      addCriterion("c.code <=", value, "code");
      return (Criteria) this;
    }

    public Criteria andCodeLike(String value) {
      addCriterion("c.code like", value, "code");
      return (Criteria) this;
    }

    public Criteria andCodeNotLike(String value) {
      addCriterion("c.code not like", value, "code");
      return (Criteria) this;
    }

    public Criteria andCodeIn(List<String> values) {
      addCriterion("c.code in", values, "code");
      return (Criteria) this;
    }

    public Criteria andCodeNotIn(List<String> values) {
      addCriterion("c.code not in", values, "code");
      return (Criteria) this;
    }

    public Criteria andCodeBetween(String value1, String value2) {
      addCriterion("c.code between", value1, value2, "code");
      return (Criteria) this;
    }

    public Criteria andCodeNotBetween(String value1, String value2) {
      addCriterion("c.code not between", value1, value2, "code");
      return (Criteria) this;
    }

    public Criteria andValueIsNull() {
      addCriterion("c.value is null");
      return (Criteria) this;
    }

    public Criteria andValueIsNotNull() {
      addCriterion("c.value is not null");
      return (Criteria) this;
    }

    public Criteria andValueEqualTo(String value) {
      addCriterion("c.value =", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueNotEqualTo(String value) {
      addCriterion("c.value <>", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueGreaterThan(String value) {
      addCriterion("c.value >", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueGreaterThanOrEqualTo(String value) {
      addCriterion("c.value >=", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueLessThan(String value) {
      addCriterion("c.value <", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueLessThanOrEqualTo(String value) {
      addCriterion("c.value <=", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueLike(String value) {
      addCriterion("c.value like", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueNotLike(String value) {
      addCriterion("c.value not like", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueIn(List<String> values) {
      addCriterion("c.value in", values, "value");
      return (Criteria) this;
    }

    public Criteria andValueNotIn(List<String> values) {
      addCriterion("c.value not in", values, "value");
      return (Criteria) this;
    }

    public Criteria andValueBetween(String value1, String value2) {
      addCriterion("c.value between", value1, value2, "value");
      return (Criteria) this;
    }

    public Criteria andValueNotBetween(String value1, String value2) {
      addCriterion("c.value not between", value1, value2, "value");
      return (Criteria) this;
    }

    public Criteria andSortIsNull() {
      addCriterion("c.sort is null");
      return (Criteria) this;
    }

    public Criteria andSortIsNotNull() {
      addCriterion("c.sort is not null");
      return (Criteria) this;
    }

    public Criteria andSortEqualTo(Long value) {
      addCriterion("c.sort =", value, "sort");
      return (Criteria) this;
    }

    public Criteria andSortNotEqualTo(Long value) {
      addCriterion("c.sort <>", value, "sort");
      return (Criteria) this;
    }

    public Criteria andSortGreaterThan(Long value) {
      addCriterion("c.sort >", value, "sort");
      return (Criteria) this;
    }

    public Criteria andSortGreaterThanOrEqualTo(Long value) {
      addCriterion("c.sort >=", value, "sort");
      return (Criteria) this;
    }

    public Criteria andSortLessThan(Long value) {
      addCriterion("c.sort <", value, "sort");
      return (Criteria) this;
    }

    public Criteria andSortLessThanOrEqualTo(Long value) {
      addCriterion("c.sort <=", value, "sort");
      return (Criteria) this;
    }

    public Criteria andSortIn(List<Long> values) {
      addCriterion("c.sort in", values, "sort");
      return (Criteria) this;
    }

    public Criteria andSortNotIn(List<Long> values) {
      addCriterion("c.sort not in", values, "sort");
      return (Criteria) this;
    }

    public Criteria andSortBetween(Long value1, Long value2) {
      addCriterion("c.sort between", value1, value2, "sort");
      return (Criteria) this;
    }

    public Criteria andSortNotBetween(Long value1, Long value2) {
      addCriterion("c.sort not between", value1, value2, "sort");
      return (Criteria) this;
    }

    public Criteria andRemarksIsNull() {
      addCriterion("c.remarks is null");
      return (Criteria) this;
    }

    public Criteria andRemarksIsNotNull() {
      addCriterion("c.remarks is not null");
      return (Criteria) this;
    }

    public Criteria andRemarksEqualTo(String value) {
      addCriterion("c.remarks =", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksNotEqualTo(String value) {
      addCriterion("c.remarks <>", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksGreaterThan(String value) {
      addCriterion("c.remarks >", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksGreaterThanOrEqualTo(String value) {
      addCriterion("c.remarks >=", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksLessThan(String value) {
      addCriterion("c.remarks <", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksLessThanOrEqualTo(String value) {
      addCriterion("c.remarks <=", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksLike(String value) {
      addCriterion("c.remarks like", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksNotLike(String value) {
      addCriterion("c.remarks not like", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksIn(List<String> values) {
      addCriterion("c.remarks in", values, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksNotIn(List<String> values) {
      addCriterion("c.remarks not in", values, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksBetween(String value1, String value2) {
      addCriterion("c.remarks between", value1, value2, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksNotBetween(String value1, String value2) {
      addCriterion("c.remarks not between", value1, value2, "remarks");
      return (Criteria) this;
    }

    public Criteria andStatusIsNull() {
      addCriterion("c.status is null");
      return (Criteria) this;
    }

    public Criteria andStatusIsNotNull() {
      addCriterion("c.status is not null");
      return (Criteria) this;
    }

    public Criteria andStatusEqualTo(Integer value) {
      addCriterion("c.status =", value, "status");
      return (Criteria) this;
    }

    public Criteria andStatusNotEqualTo(Integer value) {
      addCriterion("c.status <>", value, "status");
      return (Criteria) this;
    }

    public Criteria andStatusGreaterThan(Integer value) {
      addCriterion("c.status >", value, "status");
      return (Criteria) this;
    }

    public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
      addCriterion("c.status >=", value, "status");
      return (Criteria) this;
    }

    public Criteria andStatusLessThan(Integer value) {
      addCriterion("c.status <", value, "status");
      return (Criteria) this;
    }

    public Criteria andStatusLessThanOrEqualTo(Integer value) {
      addCriterion("c.status <=", value, "status");
      return (Criteria) this;
    }

    public Criteria andStatusIn(List<Integer> values) {
      addCriterion("c.status in", values, "status");
      return (Criteria) this;
    }

    public Criteria andStatusNotIn(List<Integer> values) {
      addCriterion("c.status not in", values, "status");
      return (Criteria) this;
    }

    public Criteria andStatusBetween(Integer value1, Integer value2) {
      addCriterion("c.status between", value1, value2, "status");
      return (Criteria) this;
    }

    public Criteria andStatusNotBetween(Integer value1, Integer value2) {
      addCriterion("c.status not between", value1, value2, "status");
      return (Criteria) this;
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table
   * sys_config
   *
   * @mbg.generated do_not_delete_during_merge Fri Dec 01 19:25:41 CST 2017
   */
  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table
   * sys_config
   *
   * @mbg.generated Fri Dec 01 19:25:41 CST 2017
   */
  public static class Criterion {

    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;

    public String getCondition() {
      return condition;
    }

    public Object getValue() {
      return value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public boolean isNoValue() {
      return noValue;
    }

    public boolean isSingleValue() {
      return singleValue;
    }

    public boolean isBetweenValue() {
      return betweenValue;
    }

    public boolean isListValue() {
      return listValue;
    }

    public String getTypeHandler() {
      return typeHandler;
    }

    protected Criterion(String condition) {
      super();
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }

    protected Criterion(String condition, Object value, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if (value instanceof List<?>) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }

    protected Criterion(String condition, Object value) {
      this(condition, value, null);
    }

    protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }

    protected Criterion(String condition, Object value, Object secondValue) {
      this(condition, value, secondValue, null);
    }
  }
}