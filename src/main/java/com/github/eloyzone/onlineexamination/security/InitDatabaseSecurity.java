package com.github.eloyzone.onlineexamination.security;


import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/26/20, 5:37 PM.
 */
@Service
public class InitDatabaseSecurity implements CommandLineRunner
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args)
    {
        if (userRepository.findAll().size() == 0)
        {
            User adminUser = new User("admin", passwordEncoder.encode("admin"), "admin", null);
            userRepository.save(adminUser);

        }
    }
}
