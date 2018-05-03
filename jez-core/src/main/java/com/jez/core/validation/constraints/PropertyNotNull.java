package com.jez.core.validation.constraints;

import com.jez.core.validation.validators.PropertyNotNullValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by JEZ on 2017/5/2.
 */
@Constraint(validatedBy = PropertyNotNullValidator.class)
@Target({java.lang.annotation.ElementType.METHOD,
    java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface PropertyNotNull {

  String message() default "{org.hibernate.validators.constraints.NotEmpty.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String[] value();

}
