package com.excilys.computerdb.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.excilys.computerdb.validation.validator.DateValidator;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
  /**
   * .
   * @return .
   */
  String message() default "{Date}";

  /**
   * .
   * @return .
   */
  Class<?>[] groups() default {};

  /**
   * .
   * @return .
   */
  Class<? extends Payload>[] payload() default {};

}
