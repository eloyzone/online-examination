package com.github.eloyzone.onlineexamination.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 2/7/21, 9:50 AM.
 */
@Documented
@Constraint(validatedBy = GapFillingQuestionTextValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GapFillingQuestionTextConstraint
{
    String message() default "{gap.filling.question.xxx.failed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
