package com.github.eloyzone.onlineexamination.controller;

import com.github.eloyzone.onlineexamination.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

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
        System.out.println("here");
        return "index";
    }
}
