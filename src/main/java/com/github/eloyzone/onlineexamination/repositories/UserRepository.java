package com.github.eloyzone.onlineexamination.repositories;

import com.github.eloyzone.onlineexamination.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 11/17/20, 7:43 PM.
 */
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByUsername(String username);
}
