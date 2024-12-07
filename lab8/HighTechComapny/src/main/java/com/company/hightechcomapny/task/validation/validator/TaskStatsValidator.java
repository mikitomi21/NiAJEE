package com.company.hightechcomapny.task.validation.validator;

import com.company.hightechcomapny.task.domain.StatsHolder;
import com.company.hightechcomapny.task.validation.binging.ValidTaskStats;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class TaskStatsValidator implements ConstraintValidator<ValidTaskStats, StatsHolder> {
    private int limit;

    @Override
    public void initialize(ValidTaskStats constraintAnnotation) {
        System.out.println("SiemaValid1");
        ConstraintValidator.super.initialize(constraintAnnotation);
        limit = constraintAnnotation.limit();
    }

    @Override
    public boolean isValid(StatsHolder value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("SiemaValid2");
        return value.getDescriptionLength() <= limit;
    }
}
