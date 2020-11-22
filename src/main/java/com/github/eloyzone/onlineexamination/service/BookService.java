package com.github.eloyzone.onlineexamination.service;

import com.github.eloyzone.onlineexamination.domain.Book;
import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 9/22/20, 4:44 PM.
 */
@Service
public class BookService
{
    @Autowired
    private BookRepository bookRepository;


    /**
     * Saves the book via BookRepository. Before saving it sets book's owner by using user object.
     * @param book
     * @param user
     * @return
     */
    public Book save(Book book, User user)
    {
        book.setUser(user);
        return bookRepository.save(book);
    }


    /**
     * This method updates the existing "Book".
     * @param book the book that should be updated, it must be existed in the database.
     * @param user the method uses the user object to check whether it is book's owner or not.
     * @return null in two cases: if there is no book with the provided book id or the current user is not book's owner.
     */
    public Book update(Book book, User user)
    {
        //todo: here instead of returning the persisted Book object or a null, it's better to return a message to controller that is everything ok or not
        Optional<Book> bookOptional = findById(book.getId());
        if (bookOptional.isPresent())
        {
            if (user.getId() == bookOptional.get().getUser().getId())
            {
                book.setUser(user);
                return bookRepository.save(book);
            }
            else return null;

        } else return null;
    }

    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long bookId)
    {
        return bookRepository.findById(bookId);
    }

    public List<Book> findByUser(User user)
    {
        return bookRepository.findByUser(user);
    }

    public void deleteById(Long bookId)
    {
        bookRepository.deleteById(bookId);
    }
}
