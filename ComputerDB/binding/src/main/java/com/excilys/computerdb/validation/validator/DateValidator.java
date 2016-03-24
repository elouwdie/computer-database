package com.excilys.computerdb.validation.validator;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.computerdb.validation.DataVerification;
import com.excilys.computerdb.validation.annotations.Date;

public class DateValidator implements ConstraintValidator<Date, String> {

  String string;

  @Override
  public void initialize(Date date) {
  }

  @Override
  public boolean isValid(String date, ConstraintValidatorContext context) {

    Locale locale = LocaleContextHolder.getLocale();
    if (date.matches("\\d{2}\\/\\d{2}\\/\\d{4}")) {
      if (locale.equals("en")) {
        if (DataVerification.isDateCorrect(Integer.parseInt(date.substring(6, 10)),
            Integer.parseInt(date.substring(0, 2)), Integer.parseInt(date.substring(3, 5)))) {
          return true;
        }
      } else {
        if (DataVerification.isDateCorrect(Integer.parseInt(date.substring(6, 10)),
            Integer.parseInt(date.substring(3, 5)), Integer.parseInt(date.substring(0, 2)))) {
          return true;
        }
      }

    } else if (date.isEmpty()) {
      return true;
    }
    return false;
  }
}
