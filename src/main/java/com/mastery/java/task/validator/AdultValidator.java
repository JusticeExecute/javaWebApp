package com.mastery.java.task.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdultValidator implements ConstraintValidator<Adult, Integer> {
    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        return age >= 18;
    }
}
