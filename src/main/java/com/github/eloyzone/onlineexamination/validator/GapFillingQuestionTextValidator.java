package com.github.eloyzone.onlineexamination.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 2/7/21, 9:52 AM.
 */
public class GapFillingQuestionTextValidator implements ConstraintValidator<GapFillingQuestionTextConstraint, String>
{
    @Override
    public void initialize(GapFillingQuestionTextConstraint constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context)
    {
        if (text == null)
        {
            return false;
        }

        String[] splitText = text.split(" ");

        boolean wrongFormat = true;
        int xxxCount = 0;

        for (String s : splitText)
        {
            if (s.equals("XXX") || s.equals("XXX.") || s.equals("XXX,") || s.equals("XXX?") || s.equals("XXX!"))
            {
                xxxCount++;
                wrongFormat = false;
            }

        }
        if (wrongFormat || xxxCount > 1)
        {
            return false;
        }

        return true;
    }
}
