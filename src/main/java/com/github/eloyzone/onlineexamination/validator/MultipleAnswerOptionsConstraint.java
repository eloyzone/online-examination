package com.github.eloyzone.onlineexamination.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 2/7/21, 9:50 AM.
 */
@Documented
@Constraint(validatedBy = MultipleAnswerOptionsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipleAnswerOptionsConstraint
{
    String message() default "No answers are selected as a correct one";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
