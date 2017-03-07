package org.ae.server.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.datanucleus.util.StringUtils;

import com.google.appengine.api.datastore.Text;

public class TextNotEmptyValidator implements
    ConstraintValidator<TextNotEmpty, Text> {

  public void initialize(TextNotEmpty constraintAnnotation) {
    // nothing to initialize
  }

  public boolean isValid(Text value, ConstraintValidatorContext context) {
    if (value == null || StringUtils.isEmpty(value.getValue())) {
      return false;
    }
    return true;
  }
}