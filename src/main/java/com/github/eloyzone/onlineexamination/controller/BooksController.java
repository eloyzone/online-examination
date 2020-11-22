package com.github.eloyzone.onlineexamination.controller;

import com.github.eloyzone.onlineexamination.domain.Book;
import com.github.eloyzone.onlineexamination.domain.GapFillingQuestion;
import com.github.eloyzone.onlineexamination.domain.ToastMessage;
import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.service.BookService;
import com.github.eloyzone.onlineexamination.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 3/16/20, 1:15 PM.
 */
@Controller
@RequestMapping("/books")
public class BooksController
{
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @InitBinder
    protected void initBinder(final WebDataBinder webDataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
        webDataBinder.addValidators(bookValidator);
    }

    @ModelAttribute("book")
    public Book newBook()
    {
        return new Book();
    }


    @GetMapping("")
    public String getBooksView(@AuthenticationPrincipal User user, ModelMap modelMap)
    {
        modelMap.put("books", bookService.findByUser(user));
        return "books";
    }

    @PostMapping("/edit/new")
    public String saveBook(@AuthenticationPrincipal User user, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult, HttpServletRequest httpServletRequest, ModelMap modelMap, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("toast", true);

        if (bindingResult.hasErrors())
        {
            List<String> errorsList = new ArrayList<>();

            for (ObjectError objectError : bindingResult.getAllErrors())
            {
                errorsList.add(objectError.getDefaultMessage());
            }


            if (book.getId() == null) // it's a new book, so we can ask to edit the entered input name
                redirectAttributes.addFlashAttribute("book", book);

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.book", bindingResult);
            redirectAttributes.addFlashAttribute("errors", errorsList);
            return "redirect:/books";
        }

        // todo: in the future check the returned value of save method, it should contain a message and an object
        if (book.getId() == null)
        {
            // new book
            bookService.save(book, user);
        } else if (book.getId() != null && book.getId() != 0)
        {
            // update book
            bookService.update(book, user);
        }

        redirectAttributes.addFlashAttribute("bookInserted", true);
        return "redirect:/books";
    }

    @DeleteMapping("/delete/{bookId}")
    public String deleteQuestion(@AuthenticationPrincipal User user, @PathVariable Long bookId, RedirectAttributes redirectAttributes)
    {
        List<ToastMessage> toastMessages = new ArrayList<>();
        redirectAttributes.addFlashAttribute("toastMessages", toastMessages);

        Optional<Book> optionalBook = bookService.findById(bookId);

        if (optionalBook.isPresent() && optionalBook.get().getUser().getId() != user.getId())
        {
            return "redirect:/books";
        } else
        {
            String message = messageSource.getMessage("book.deleted.successfully", null, LocaleContextHolder.getLocale());
            toastMessages.add(new ToastMessage(message, ToastMessage.SUCCESS));
            bookService.deleteById(bookId);
        }

        return "redirect:/books";
    }
}
