package com.github.eloyzone.onlineexamination.service;

import com.github.eloyzone.onlineexamination.config.DateUtil;
import com.github.eloyzone.onlineexamination.domain.*;
import com.github.eloyzone.onlineexamination.repositories.QuestionRepository;
import com.github.eloyzone.onlineexamination.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 10/8/20, 8:51 AM.
 */
@Service
public class QuestionService
{
    private final static int[] LEVEL_TWO_DAYS = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63};
    private final static int[] LEVEL_THREE_DAYS = {2, 6, 10, 14, 18, 22, 26, 30, 38, 42, 46, 50, 54, 58, 62};
    private final static int[] LEVEL_FOUR_DAYS = {4, 13, 20, 29, 36, 45, 52, 61};
    private final static int[] LEVEL_FIVE_DAYS = {12, 28, 44, 60};
    private final static int[] LEVEL_SIX_DAYS = {24, 59};
    private final static int[] LEVEL_SEVEN_DAYS = {56};


    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;


    public void delete(Long questionId)
    {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent())
        {
            questionRepository.deleteById(questionId);
        }
    }

    public Optional<Question> getById(Long questionId)
    {
        return questionRepository.findById(questionId);
    }

    public List<Question> getTodayQuestions(User user)
    {
        user = userRepository.findById(user.getId()).get();
        int level = whichLevelToReview(user);
        LocalDate todayDate = DateUtil.getTodayDate();
        List<Integer> levels = new ArrayList<>();
        levels.add(1);
        levels.add(level);
        return questionRepository.findByLevelInAndCreatedDateNotAndBook_User_id(levels, todayDate, user.getId());
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
}
