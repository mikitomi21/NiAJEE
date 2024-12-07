package com.company.hightechcomapny.task.validation.binging;

import com.company.hightechcomapny.task.validation.validator.TaskStatsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TaskStatsValidator.class)
@Documented

public @interface ValidTaskStats {
    String message() default "stat DescriptionLength should be lower of equal than {limit}";

    Class<?>[] groups() default {};

    /**
     * @return additional payload for programmer, not used by BeansValidation
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * @return limit for sum of statistics
     */
    int limit() default 20;

}
