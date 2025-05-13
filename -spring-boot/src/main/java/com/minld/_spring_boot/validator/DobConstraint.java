package com.minld._spring_boot.validator;

import java.lang.annotation.*;
import java.lang.annotation.ElementType.*;

import jakarta.validation.Constraint;

@Target({ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DobValidator.class})
public @interface DobConstraint {
    java.lang.String message() default "{Invalid data of birth}";

    int min();

    java.lang.Class<?>[] groups() default {};

    java.lang.Class<? extends jakarta.validation.Payload>[] payload() default {};
}
