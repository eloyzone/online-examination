package com.github.eloyzone.onlineexamination.validator;

import com.github.eloyzone.onlineexamination.domain.Book;
import com.github.eloyzone.onlineexamination.repositories.BookRepository;
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
 * Created in 10/2/20, 6:07 PM.
 */
@Component
public class BookValidator implements Validator
{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        Book book = (Book) target;
        Optional<Book> bookOptional = bookRepository.findByName(book.getName());
        if(bookOptional.isPresent())
        {
            Locale locale = LocaleContextHolder.getLocale();
            String[] args = {book.getName()};
            String message = messageSource.getMessage("book.duplicated.name.exception", args, locale);
            errors.rejectValue("name", "book.duplicated.name.exception", message);
        }
    }
}
