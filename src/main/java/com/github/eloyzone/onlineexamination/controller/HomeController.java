package com.github.eloyzone.onlineexamination.controller;

import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.service.GapFillingQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 3/16/20, 1:15 PM.
 */
@Controller
@RequestMapping("/")
public class HomeController
{
    @Autowired
    private GapFillingQuestionService gapFillingQuestionService;

    @GetMapping("")
    public String getIndexView(@AuthenticationPrincipal User user, ModelMap modelMap)
    {
        Long userId = user.getId();

        modelMap.put("toReviewCount", gapFillingQuestionService.getTodayQuestions(user).size());
        modelMap.put("levelOneCount", gapFillingQuestionService.getNumberOfQuestionsInSpecificLevel(userId, 1));
        modelMap.put("levelTwoCount", gapFillingQuestionService.getNumberOfQuestionsInSpecificLevel(userId, 2));
        modelMap.put("levelThreeCount", gapFillingQuestionService.getNumberOfQuestionsInSpecificLevel(userId, 3));
        modelMap.put("levelFourCount", gapFillingQuestionService.getNumberOfQuestionsInSpecificLevel(userId, 4));
        modelMap.put("levelFiveCount", gapFillingQuestionService.getNumberOfQuestionsInSpecificLevel(userId, 5));
        modelMap.put("levelSixCount", gapFillingQuestionService.getNumberOfQuestionsInSpecificLevel(userId, 6));
        modelMap.put("levelSevenCount", gapFillingQuestionService.getNumberOfQuestionsInSpecificLevel(userId, 7));

        return "index";
    }
}
