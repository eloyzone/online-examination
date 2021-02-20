package com.github.eloyzone.onlineexamination.service;

import com.github.eloyzone.onlineexamination.config.DateUtil;
import com.github.eloyzone.onlineexamination.domain.*;
import com.github.eloyzone.onlineexamination.repositories.MultipleChoiceQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/8/20, 8:51 AM.
 */
@Service
public class MultipleChoiceQuestionService
{
    @Autowired
    private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

    @Autowired
    private BookService bookService;

    public boolean save(MultipleChoiceQuestion multipleChoiceQuestion, Long bookId)
    {
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (optionalBook.isPresent())
        {
            multipleChoiceQuestion.setBook(optionalBook.get());
            update(multipleChoiceQuestion);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean update(MultipleChoiceQuestion multipleChoiceQuestion)
    {
        if (multipleChoiceQuestion.getDescription().equals("<p><br data-cke-filler=\"true\"></p>"))
            multipleChoiceQuestion.setDescription("");

        if (multipleChoiceQuestion.getId() == null)
        {
            multipleChoiceQuestion.setCreatedDate(DateUtil.getTodayDate());
            multipleChoiceQuestion.setLastReviewDate(DateUtil.getTodayDate());
        }
        multipleChoiceQuestionRepository.save(multipleChoiceQuestion);
        return true;
    }

    public QuestionCheckingResponse checkQuestionAnswer(MultipleChoiceQuestion multipleChoiceQuestion)
    {
        Optional<MultipleChoiceQuestion> optionalMultipleChoiceQuestion = getById(multipleChoiceQuestion.getId());

        if (optionalMultipleChoiceQuestion.isPresent())
        {
            MultipleChoiceQuestion multipleChoiceQuestionCached = optionalMultipleChoiceQuestion.get();
            QuestionCheckingResponse questionCheckingResponse;

            boolean correctAnswer = true;

            List<MultipleAnswerOption> multipleAnswerOptionsCached = multipleChoiceQuestionCached.getMultipleAnswerOptions();
            for (MultipleAnswerOption multipleAnswerOptionCached : multipleAnswerOptionsCached)
            {
                correctAnswer = false;
                List<MultipleAnswerOption> multipleAnswerOptions = multipleChoiceQuestion.getMultipleAnswerOptions();
                for (MultipleAnswerOption multipleAnswerOption : multipleAnswerOptions)
                {
                    if ((multipleAnswerOptionCached.isAnswer() == multipleAnswerOption.isAnswer()) && (multipleAnswerOptionCached.getId() == multipleAnswerOption.getId()))
                    {
                        correctAnswer = true;
                        break;
                    }
                }
            }

            if (correctAnswer)
            {
                if (multipleChoiceQuestionCached.getLevel() != 7)
                    multipleChoiceQuestionCached.setLevel(multipleChoiceQuestionCached.getLevel() + 1);
                questionCheckingResponse = new QuestionCheckingResponse(multipleChoiceQuestionCached, QuestionCheckingResponse.CORRECT);
            } else
            {
                multipleChoiceQuestionCached.setLevel(1);
                questionCheckingResponse = new QuestionCheckingResponse(multipleChoiceQuestionCached, QuestionCheckingResponse.WRONG);
            }

            multipleChoiceQuestionCached.setLastReviewDate(DateUtil.getTodayDate());
            multipleChoiceQuestionRepository.save(multipleChoiceQuestionCached);

            return questionCheckingResponse;
        } else
        {
            return new QuestionCheckingResponse("Unexpected error happened");
        }
    }

    private Optional<MultipleChoiceQuestion> getById(Long id)
    {
        return multipleChoiceQuestionRepository.findById(id);
    }
}
