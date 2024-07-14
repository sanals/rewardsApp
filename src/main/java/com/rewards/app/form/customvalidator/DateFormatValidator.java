package com.rewards.app.form.customvalidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateFormatValidator implements ConstraintValidator<DateTimeFormat, LocalDateTime> {

	  private String format;

	  @Override
	  public void initialize(DateTimeFormat constraintAnnotation) {
	    this.format = constraintAnnotation.format();
	  }

	  @Override
	  public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
	    try {
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
	      formatter.parse(value.toString());
	      return true;
	    } catch (DateTimeParseException e) {
	      return false;
	    }
	  }
	}
