package com.jez.core.validation.validators;

import com.jez.core.validation.constraints.Enum;
import java.lang.reflect.Method;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JEZ on 2017/5/10.
 */
public class EnumValidator implements ConstraintValidator<Enum, Object> {

  private Class<?> javaType;

  private String method;

  @Override
  public void initialize(Enum anEnum) {
    javaType = anEnum.javaType();
    method = anEnum.method();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    if (o == null || o.getClass().isAssignableFrom(javaType) || (
        o.getClass().isAssignableFrom(String.class) && StringUtils.isEmpty((String) o))) {
      return true;
    }
    return isValid(o);
  }

  private boolean isValid(Object o) {
    Method valueAccessor;
    try {
      valueAccessor = javaType.getMethod(method);
    } catch (NoSuchMethodException e) {
      throw new IllegalArgumentException(String.format("Invalid enum method <{}>.", method), e);
    }
    for (Object enm : javaType.getEnumConstants()) {
      if (o.equals(getEnumValue(valueAccessor, enm))) {
        return true;
      }
    }
    return false;
  }

  private Object getEnumValue(Method accessor, Object enm) {
    try {
      return accessor.invoke(enm);
    } catch (Exception e) {
      throw new IllegalArgumentException(
          String.format("Failed to invoke method <{}> for <{}>.", method, enm), e);
    }
  }

}
