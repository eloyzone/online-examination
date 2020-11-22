package com.github.eloyzone.onlineexamination.service;

import com.github.eloyzone.onlineexamination.config.DateUtil;
import com.github.eloyzone.onlineexamination.domain.*;
import com.github.eloyzone.onlineexamination.repositories.GapFillingQuestionRepository;
import com.github.eloyzone.onlineexamination.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/8/20, 8:51 AM.
 */
@Service
public class GapFillingQuestionService
{
    private final static int[] LEVEL_TWO_DAYS = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63};
    private final static int[] LEVEL_THREE_DAYS = {2, 6, 10, 14, 18, 22, 26, 30, 38, 42, 46, 50, 54, 58, 62};
    private final static int[] LEVEL_FOUR_DAYS = {4, 13, 20, 29, 36, 45, 52, 61};
    private final static int[] LEVEL_FIVE_DAYS = {12, 28, 44, 60};
    private final static int[] LEVEL_SIX_DAYS = {24, 59};
    private final static int[] LEVEL_SEVEN_DAYS = {56};

    @Autowired
    private GapFillingQuestionRepository gapFillingQuestionRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    public boolean save(GapFillingQuestion gapFillingQuestion, Long bookId)
    {
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (optionalBook.isPresent())
        {
            gapFillingQuestion.setBook(optionalBook.get());
            update(gapFillingQuestion);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean update(GapFillingQuestion gapFillingQuestion)
    {
        if (gapFillingQuestion.getDescription().equals("<p><br data-cke-filler=\"true\"></p>"))
            gapFillingQuestion.setDescription("");

        if (gapFillingQuestion.getId() == null)
        {
            gapFillingQuestion.setCreatedDate(DateUtil.getTodayDate());
            gapFillingQuestion.setLastReviewDate(DateUtil.getTodayDate());
        }
        gapFillingQuestionRepository.save(gapFillingQuestion);
        return true;
    }

    public Optional<GapFillingQuestion> getById(Long id)
    {
        return gapFillingQuestionRepository.findById(id);
    }

    public List<GapFillingQuestion> getAll()
    {
        return gapFillingQuestionRepository.findAll();
    }

    public List<GapFillingQuestion> getTodayQuestions(User user)
    {
        user = userRepository.findById(user.getId()).get();
        int level = whichLevelToReview(user);
        if (level != 1)
        {
            // level one plus others
            return gapFillingQuestionRepository.findByUserIdAndLevels(user.getId(), level);
        } else
        {
            // just level one
            return gapFillingQuestionRepository.findByUserIdAndLevels(user.getId(), 1);
        }
    }

    public int whichLevelToReview(User user)
    {
        LocalDate startingRoundLocalDate = user.getStartingRoundLocalDate();

        if (startingRoundLocalDate == null)
        {
            startingRoundLocalDate = DateUtil.getTodayDate();
            user.setStartingRoundLocalDate(startingRoundLocalDate);
            userRepository.save(user);
        }

        int passedDayDuration = Period.between(startingRoundLocalDate, DateUtil.getTodayDate()).getDays() + 1;

        if (passedDayDuration < 64)
        {
            for (int dayLevel : LEVEL_TWO_DAYS)
            {
                if (dayLevel == passedDayDuration) return 2;
            }

            for (int dayLevel : LEVEL_THREE_DAYS)
            {
                if (dayLevel == passedDayDuration) return 3;
            }

            for (int dayLevel : LEVEL_FOUR_DAYS)
            {
                if (dayLevel == passedDayDuration) return 4;
            }
            for (int dayLevel : LEVEL_FIVE_DAYS)
            {
                if (dayLevel == passedDayDuration) return 5;
            }
            for (int dayLevel : LEVEL_SIX_DAYS)
            {
                if (dayLevel == passedDayDuration) return 6;
            }

            for (int dayLevel : LEVEL_SEVEN_DAYS)
            {
                if (dayLevel == passedDayDuration) return 7;
            }
        } else
        {
            startingRoundLocalDate = DateUtil.getTodayDate();
            user.setStartingRoundLocalDate(startingRoundLocalDate);
            userRepository.save(user);
        }
        return 1;
    }

    public QuestionCheckingResponse checkQuestionAnswer(QuestionCheckingRequest questionCheckingRequest)
    {
        Optional<GapFillingQuestion> optionalGapFillingQuestion = getById(questionCheckingRequest.getQuestionId());

        if (optionalGapFillingQuestion.isPresent())
        {
            GapFillingQuestion gapFillingQuestion = optionalGapFillingQuestion.get();
            QuestionCheckingResponse questionCheckingResponse;
            if (questionCheckingRequest.getEnteredAnswer().equalsIgnoreCase(gapFillingQuestion.getAnswer()))
            {
                if (gapFillingQuestion.getLevel() != 7) gapFillingQuestion.setLevel(gapFillingQuestion.getLevel() + 1);
                questionCheckingResponse = new QuestionCheckingResponse(QuestionCheckingResponse.CORRECT, gapFillingQuestion.getAnswer(), gapFillingQuestion.getDescription());

            } else
            {
                gapFillingQuestion.setLevel(1);
                questionCheckingResponse = new QuestionCheckingResponse(QuestionCheckingResponse.WRONG, gapFillingQuestion.getAnswer(), gapFillingQuestion.getDescription());
            }

            gapFillingQuestion.setLastReviewDate(DateUtil.getTodayDate());
            gapFillingQuestionRepository.save(gapFillingQuestion);
            return questionCheckingResponse;
        } else
        {
            return new QuestionCheckingResponse("Unexpected error happened");
        }
    }

    public Integer getNumberOfQuestionsInSpecificLevel(Long userId, Integer level)
    {
        return gapFillingQuestionRepository.numberOfQuestionsInSpecificLevel(userId, level);
    }

    public void delete(Long questionId)
    {
        Optional<GapFillingQuestion> gapFillingQuestionOptional = gapFillingQuestionRepository.findById(questionId);
        if (gapFillingQuestionOptional.isPresent())
        {
            gapFillingQuestionRepository.deleteById(questionId);
        }
    }
}
