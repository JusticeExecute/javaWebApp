package com.mastery.java.task.validator;

import com.mastery.java.task.dto.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumNamePatternValidator implements ConstraintValidator<EnumNamePattern, Gender> {
    private Gender[] subset;

    @Override
    public void initialize(EnumNamePattern constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}