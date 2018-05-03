package com.jez.core.validation.validators;

import com.jez.core.validation.constraints.PropertyNotNull;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JEZ on 2017/5/2.
 */
public class PropertyNotNullValidator implements ConstraintValidator<PropertyNotNull, Object> {

  private String[] fields;

  @Override
  public void initialize(PropertyNotNull propertyNotNull) {
    fields = propertyNotNull.value();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext context) {
    if (o == null) {
      return true;
    }
    Object value;
    for (String field : fields) {
      try {
        value = PropertyUtils.getProperty(o, field);
      } catch (Exception e) {
        throw new IllegalArgumentException(
            String.format("Failed to get object property <{}>.", field), e);
      }
      if (value == null || (value.getClass().isAssignableFrom(String.class) && StringUtils
          .isEmpty((String) value))) {
        return false;
      }
    }
    return true;
  }
}
