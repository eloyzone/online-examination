package com.github.eloyzone.onlineexamination.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 11/17/20, 6:45 PM.
 */
public class MainEncodedPassword
{
    public static void main(String[] args)
    {
        System.out.println(new BCryptPasswordEncoder().encode("myjava123"));
    }
}
