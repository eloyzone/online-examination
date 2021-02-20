package com.github.eloyzone.onlineexamination.controller;

import com.github.eloyzone.onlineexamination.domain.*;
import com.github.eloyzone.onlineexamination.service.BookService;
import com.github.eloyzone.onlineexamination.service.GapFillingQuestionService;
import com.github.eloyzone.onlineexamination.service.MultipleChoiceQuestionService;
import com.github.eloyzone.onlineexamination.service.QuestionService;
import com.github.eloyzone.onlineexamination.validator.GapFillingQuestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/books/{bookId}/questions")
public class QuestionsController
{
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private GapFillingQuestionService gapFillingQuestionService;

    @Autowired
    private MultipleChoiceQuestionService multipleChoiceQuestionService;

    @Autowired
    private BookService bookService;

    @Autowired
    private GapFillingQuestionValidator gapFillingQuestionValidator;

    @Autowired
    private QuestionService questionService;

    @InitBinder
    protected void initBinder(final WebDataBinder webDataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /**
     * As we want to use the redirect approach after validation we need a new GapFillingQuestion. If we used the dispatched
     * approach instead of redirecting, we could have no ModelAttribute
     *
     * @return
     */
    @ModelAttribute("gapFillingQuestion")
    public GapFillingQuestion newGapFillingQuestion()
    {
        return new GapFillingQuestion();
    }

    @ModelAttribute("multipleChoiceQuestion")
    public MultipleChoiceQuestion newMultipleChoiceQuestion()
    {
        return new MultipleChoiceQuestion();
    }

    @ModelAttribute("multipleAnswerOption")
    public MultipleAnswerOption newMultipleAnswerOption()
    {
        return new MultipleAnswerOption();
    }

    @GetMapping("")
    public String getBookQuestionsView(@AuthenticationPrincipal User user, @PathVariable Long bookId, ModelMap modelMap)
    {
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (optionalBook.isPresent() && optionalBook.get().getUser().getId() == user.getId())
        {
            modelMap.put("bookId", bookId);
            List<Question> questions = optionalBook.get().getQuestions();
            List<GapFillingQuestion> gapFillingQuestions = new ArrayList<>();
            List<MultipleChoiceQuestion> multipleChoiceQuestions = new ArrayList<>();
            for (Question question : questions)
            {
                if (question.getClass() == GapFillingQuestion.class)
                {
                    gapFillingQuestions.add((GapFillingQuestion) question);
                } else if (question.getClass() == MultipleChoiceQuestion.class)
                {
                    multipleChoiceQuestions.add((MultipleChoiceQuestion) question);
                }
            }
            modelMap.put("gapFillingQuestions", gapFillingQuestions);
            modelMap.put("multipleChoiceQuestions", multipleChoiceQuestions);
            return "questions";
        } else
        {
            //todo: show errors, no match found when book is empty
            return "redirect:/books";
        }
    }

    @GetMapping("/gapFillingQuestion/new")
    public String createNewQuestionView(@AuthenticationPrincipal User user,
                                        @PathVariable Long bookId,
                                        ModelMap modelMap)
    {
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (optionalBook.isPresent() && optionalBook.get().getUser().getId() == user.getId())
        {
            modelMap.put("saveLink", "/books/" + bookId + "/questions/gapFillingQuestion/new/save");
            modelMap.put("saveAndNewLink", "/books/" + bookId + "/questions/gapFillingQuestion/new/saveAndNew");
            return "newQuestion";
        } else
        {
            return "redirect:/books";
        }
    }

    @GetMapping("/multipleChoiceQuestion/new")
    public String createNewMultipleChoiceQuestionView(@AuthenticationPrincipal User user,
                                                      @PathVariable Long bookId,
                                                      ModelMap modelMap)
    {
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (optionalBook.isPresent() && optionalBook.get().getUser().getId() == user.getId())
        {
            modelMap.put("saveLink", "/books/" + bookId + "/questions/multipleChoiceQuestion/new/save");
            modelMap.put("saveAndNewLink", "/books/" + bookId + "/questions/multipleChoiceQuestion/new/saveAndNew");
            return "new-multiple-choice-question";
        } else
        {
            return "redirect:/books";
        }
    }

    @GetMapping("/{questionId}")
    public String getQuestionView(@AuthenticationPrincipal User user,
                                  @PathVariable Long bookId,
                                  @PathVariable Long questionId,
                                  ModelMap modelMap)
    {
        Optional<Question> questionOptional = questionService.getById(questionId);
        Optional<Book> optionalBook = bookService.findById(bookId);

        if (questionOptional.isPresent() && optionalBook.isPresent() && optionalBook.get().getUser().getId() == user.getId())
        {
            Question question = questionOptional.get();
            if (question.getClass() == GapFillingQuestion.class)
            {
                modelMap.put("saveLink", "/books/" + bookId + "/questions/gapFillingQuestion/new/save");
                modelMap.put("saveAndNewLink", "/books/" + bookId + "/questions/gapFillingQuestion/new/saveAndNew");
                modelMap.put("gapFillingQuestion", question);
                return "newQuestion";
            } else if (question.getClass() == MultipleChoiceQuestion.class)
            {
                modelMap.put("saveLink", "/books/" + bookId + "/questions/multipleChoiceQuestion/new/save");
                modelMap.put("saveAndNewLink", "/books/" + bookId + "/questions/multipleChoiceQuestion/new/saveAndNew");
                modelMap.put("multipleChoiceQuestion", question);
                return "new-multiple-choice-question";
            }
            return "redirect:/books"; // todo: remove it! it is here just for compilation
        } else
        {
            //todo: no question found for edit
            return "redirect:/books";
        }
    }


    @PostMapping(value = {"/gapFillingQuestion/new/save", "/gapFillingQuestion/new/saveAndNew"})
    public String createNewGapFillingQuestion(@AuthenticationPrincipal User user,
                                              @PathVariable Long bookId,
                                              @Valid GapFillingQuestion gapFillingQuestion,
                                              BindingResult bindingResult,
                                              RedirectAttributes redirectAttributes,
                                              HttpServletRequest httpServletRequest)
    {
        redirectAttributes.addFlashAttribute("toast", true);
        List<ToastMessage> toastMessages = new ArrayList<>();
        redirectAttributes.addFlashAttribute("toastMessages", toastMessages);

        String servletRequestPath = httpServletRequest.getServletPath();

        Optional<Book> optionalBook = bookService.findById(bookId);

        if (optionalBook.isPresent() && optionalBook.get().getUser().getId() != user.getId())
        {
            return "redirect:/books";
        }


        if (bindingResult.hasErrors())
        {
            for (ObjectError objectError : bindingResult.getAllErrors())
            {
                toastMessages.add(new ToastMessage(objectError.getDefaultMessage(), ToastMessage.ERROR));
            }
            redirectAttributes.addFlashAttribute("gapFillingQuestion", gapFillingQuestion);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.gapFillingQuestion", bindingResult);
            return "redirect:/books/" + bookId + "/questions/gapFillingQuestion/new";
        }

        if (gapFillingQuestion.getId() == null && gapFillingQuestionService.save(gapFillingQuestion, bookId))
        {
            // saving new question
            String message = messageSource.getMessage("question.created.successfully", null, LocaleContextHolder.getLocale());
            toastMessages.add(new ToastMessage(message, ToastMessage.SUCCESS));
        } else if (gapFillingQuestion.getId() != null && gapFillingQuestionService.update(gapFillingQuestion))
        {
            // update existing question
            String message = messageSource.getMessage("question.updated.successfully", null, LocaleContextHolder.getLocale());
            toastMessages.add(new ToastMessage(message, ToastMessage.SUCCESS));

        } else
        {
            String message = messageSource.getMessage("question.error.occurred.while.updating.or.creating", null, LocaleContextHolder.getLocale());
            toastMessages.add(new ToastMessage(message, ToastMessage.ERROR));
        }

        if (servletRequestPath.contains("saveAndNew"))
        {
            return "redirect:/books/" + bookId + "/questions/gapFillingQuestion/new";
        } else
        {
            return "redirect:/books/" + bookId + "/questions";

        }
    }


    @PostMapping(value = {"/multipleChoiceQuestion/new/save", "/multipleChoiceQuestion/new/saveAndNew"})
    public String createMultipleChoiceQuestion(@AuthenticationPrincipal User user,
                                               @PathVariable Long bookId,
                                               @Valid MultipleChoiceQuestion multipleChoiceQuestion,
                                               BindingResult bindingResult,
                                               RedirectAttributes redirectAttributes,
                                               HttpServletRequest httpServletRequest)
    {
        redirectAttributes.addFlashAttribute("toast", true);
        List<ToastMessage> toastMessages = new ArrayList<>();
        redirectAttributes.addFlashAttribute("toastMessages", toastMessages);

        String servletRequestPath = httpServletRequest.getServletPath();

        Optional<Book> optionalBook = bookService.findById(bookId);

        if (optionalBook.isPresent() && optionalBook.get().getUser().getId() != user.getId())
        {
            return "redirect:/books";
        }

        if (bindingResult.hasErrors())
        {
            for (ObjectError objectError : bindingResult.getAllErrors())
            {
                toastMessages.add(new ToastMessage(objectError.getDefaultMessage(), ToastMessage.ERROR));
            }
            redirectAttributes.addFlashAttribute("multipleChoiceQuestion", multipleChoiceQuestion);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.multipleChoiceQuestion", bindingResult);
            return "redirect:/books/" + bookId + "/questions/multipleChoiceQuestion/new";


        }
        multipleChoiceQuestionService.save(multipleChoiceQuestion, bookId);


        if (servletRequestPath.contains("saveAndNew"))
        {
            return "redirect:/books/" + bookId + "/questions/gapFillingQuestion/new";
        } else
        {
            return "redirect:/books/" + bookId + "/questions";

        }
    }

    @DeleteMapping("/delete/{questionId}")
    public String deleteQuestion(@AuthenticationPrincipal User user,
                                 @PathVariable Long bookId,
                                 @PathVariable Long questionId,
                                 RedirectAttributes redirectAttributes)
    {
        List<ToastMessage> toastMessages = new ArrayList<>();
        redirectAttributes.addFlashAttribute("toastMessages", toastMessages);

        Optional<Book> optionalBook = bookService.findById(bookId);

        if (optionalBook.isPresent() && optionalBook.get().getUser().getId() != user.getId())
        {
            return "redirect:/books";
        }


        Optional<Question> question = questionService.getById(questionId);

        if (question.isPresent())
        {
            questionService.delete(questionId);
            String message = messageSource.getMessage("question.deleted.successfully", null, LocaleContextHolder.getLocale());
            toastMessages.add(new ToastMessage(message, ToastMessage.SUCCESS));
        } else
        {
            String message = messageSource.getMessage("question.error.occurred.while.updating.or.creating", null, LocaleContextHolder.getLocale());
            toastMessages.add(new ToastMessage(message, ToastMessage.ERROR));
        }

        return "redirect:/books/" + bookId + "/questions";
    }
}
