package com.github.eloyzone.onlineexamination.validator;

import com.github.eloyzone.onlineexamination.domain.Book;
import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.repositories.BookRepository;
import com.github.eloyzone.onlineexamination.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Locale;
import java.util.Optional;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 11/18/20, 4:39 PM.
 */
@Component
public class UserValidator implements Validator
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        User user = (User) target;
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent())
        {
            Locale locale = LocaleContextHolder.getLocale();
            String[] args = {user.getUsername()};
            String message = messageSource.getMessage("username.duplicated.exception", args, locale);
            errors.rejectValue("username", "username.duplicated.exception", message);
        }
    }
}
