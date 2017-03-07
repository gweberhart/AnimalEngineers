package org.ae.server.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.datanucleus.util.StringUtils;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

  public void initialize(NotEmpty constraintAnnotation) {
    // nothing to initialize
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || StringUtils.isEmpty(value)) {
      return false;
    }
    return true;
  }
}