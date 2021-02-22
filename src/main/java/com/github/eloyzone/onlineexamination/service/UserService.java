package com.github.eloyzone.onlineexamination.service;

import com.github.eloyzone.onlineexamination.domain.Authority;
import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 11/18/20, 6:51 PM.
 */

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user)
    {
        Authority authority = new Authority(user, "ROLE_USER");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getAuthorities().add(authority);

        return userRepository.save(user);
    }
}
