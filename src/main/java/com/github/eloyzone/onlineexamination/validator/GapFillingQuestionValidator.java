package com.github.eloyzone.onlineexamination.validator;

import com.github.eloyzone.onlineexamination.domain.GapFillingQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Locale;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/2/20, 6:07 PM.
 */
@Component
public class GapFillingQuestionValidator implements Validator
{
    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return GapFillingQuestion.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        GapFillingQuestion gapFillingQuestion = (GapFillingQuestion) target;
        String[] splitText = gapFillingQuestion.getText().split(" ");

        boolean wrongFormat = true;
        int xxxCount = 0;

        for (String s : splitText)
        {
            if (s.equals("XXX") || s.equals("XXX."))
            {
                xxxCount++;
                wrongFormat = false;
            }

        }
        if (wrongFormat == true || xxxCount > 1)
        {
            Locale locale = LocaleContextHolder.getLocale();
            String message = messageSource.getMessage("gap.filling.question.xxx.failed", null, locale);
            errors.rejectValue("text", "gap.filling.question.xxx.failed", message);
        }
    }
}
