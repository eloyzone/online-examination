package com.github.eloyzone.onlineexamination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 3/16/20, 1:15 PM.
 */
@Controller
@RequestMapping("/")
public class HomeController
{
    @GetMapping("")
    public String getIndexView()
    {
        return "index";
    }
}
