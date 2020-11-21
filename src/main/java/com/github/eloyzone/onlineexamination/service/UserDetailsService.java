package com.github.eloyzone.onlineexamination.service;

import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 11/17/20, 7:43 PM.
 */

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    /**
     * This method executes for authenticating the user during login process(only once till next login)
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent())
        {
            return new com.github.eloyzone.onlineexamination.security.UserDetails(userOptional.get());
        }
        else
        {
            throw new UsernameNotFoundException("Invalid Username and Password !!");
        }
    }
}
