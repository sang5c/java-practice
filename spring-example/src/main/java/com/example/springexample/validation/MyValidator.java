package com.example.springexample.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyValidator implements ConstraintValidator<CustomValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("########");
        return value.contains("gg");
    }
}
