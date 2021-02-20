package com.github.eloyzone.onlineexamination.validator;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 2/7/21, 9:52 AM.
 */

import com.github.eloyzone.onlineexamination.domain.MultipleAnswerOption;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MultipleAnswerOptionsValidator implements ConstraintValidator<MultipleAnswerOptionsConstraint, List<MultipleAnswerOption>>
{
    @Override
    public void initialize(MultipleAnswerOptionsConstraint constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(List<MultipleAnswerOption> value, ConstraintValidatorContext context)
    {
        for (MultipleAnswerOption multipleAnswerOption : value)
        {
            if(multipleAnswerOption.isAnswer() == true)
            {
                return true;
            }
        }
        return false;
    }
}
