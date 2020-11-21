package com.github.eloyzone.onlineexamination.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 4/12/20, 12:51 PM.
 */
@ControllerAdvice
public class ApplicationExceptionHandler
{
    @ExceptionHandler(ApplicationException.class)
    public String handleException()
    {
        System.out.println("In global exception handler");
        return "error";
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public String exception(NoHandlerFoundException e) {
        System.out.println("In global exception handler - NoHandlerFoundException");
        return "error";
    }
}
