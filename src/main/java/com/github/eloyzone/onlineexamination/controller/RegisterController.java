package com.github.eloyzone.onlineexamination.controller;

import com.github.eloyzone.onlineexamination.domain.Book;
import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.service.UserService;
import com.github.eloyzone.onlineexamination.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 11/18/20, 4:07 PM.
 */
@Controller
public class RegisterController
{
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @InitBinder
    protected void initBinder(final WebDataBinder webDataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
        webDataBinder.addValidators(userValidator);
    }

    @ModelAttribute("user")
    public User userInstance()
    {
        return new User();
    }

    @GetMapping("/register")
    public String getRegisterView()
    {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("toast", true);

        if (bindingResult.hasErrors())
        {
            List<String> errorsList = new ArrayList<>();

            for (ObjectError objectError : bindingResult.getAllErrors())
            {
                errorsList.add(objectError.getDefaultMessage());
            }

            if (user.getId() == null) // it's a new user, so we can ask to edit the entered input
                redirectAttributes.addFlashAttribute("user", user);

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("errors", errorsList);
            return "redirect:/register";
        }

        User registeredUser = userService.save(user);

        if (registeredUser != null)
        {
            redirectAttributes.addFlashAttribute("userRegistered", true);
            return "redirect:/login";
        } else
        {
            Locale locale = LocaleContextHolder.getLocale();
            String message = messageSource.getMessage("registration.unexpected.error", null, locale);
            List<String> errorsList = new ArrayList<>();
            errorsList.add(message);
            redirectAttributes.addFlashAttribute("errors", errorsList);
            return "redirect:/register";
        }
    }
}
