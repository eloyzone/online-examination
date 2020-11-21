package com.github.eloyzone.onlineexamination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 11/17/20, 4:42 PM.
 */
@Controller
@RequestMapping("")
public class LoginController
{
    @GetMapping("/login")
    public String getLoginView()
    {
        return "login";
    }
}
