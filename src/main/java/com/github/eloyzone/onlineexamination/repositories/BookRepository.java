package com.github.eloyzone.onlineexamination.repositories;

import com.github.eloyzone.onlineexamination.domain.Book;
import com.github.eloyzone.onlineexamination.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 9/22/20, 4:45 PM.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>
{
    Optional<Book> findByName(String name);

    List<Book> findByUser(User user);
}
