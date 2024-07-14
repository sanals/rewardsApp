package com.rewards.app.form.customvalidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

//@Constraint(validatedBy = DateFormatValidator.class)
@Constraint(validatedBy = {})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeFormat {

  String format() default "yyyy-MM-dd HH:mm"; // Specify default format

  String message() default "Invalid date and time format";
}