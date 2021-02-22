package com.github.eloyzone.onlineexamination.controller;

import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.repositories.QuestionRepository;
import com.github.eloyzone.onlineexamination.service.GapFillingQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 3/16/20, 1:15 PM.
 */
@Controller
@RequestMapping("/")
public class HomeController
{
    @Autowired
    private GapFillingQuestionService gapFillingQuestionService;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("")
    public String getIndexView(@AuthenticationPrincipal User user, ModelMap modelMap)
    {
        Long userId = user.getId();

        Map<Integer, Integer> questionsLevelsCountMap = questionRepository.questionsLevelsCountMapped(userId);

        if(questionsLevelsCountMap.containsKey(new Integer(1)))
            modelMap.put("levelOneCount", questionsLevelsCountMap.get(1));

        if(questionsLevelsCountMap.containsKey(new Integer(2)))
            modelMap.put("levelTwoCount", questionsLevelsCountMap.get(2));

        if(questionsLevelsCountMap.containsKey(new Integer(3)))
            modelMap.put("levelThreeCount", questionsLevelsCountMap.get(3));

        if(questionsLevelsCountMap.containsKey(new Integer(4)))
            modelMap.put("levelFourCount", questionsLevelsCountMap.get(4));

        if(questionsLevelsCountMap.containsKey(new Integer(5)))
            modelMap.put("levelFiveCount", questionsLevelsCountMap.get(5));

        if(questionsLevelsCountMap.containsKey(new Integer(6)))
            modelMap.put("levelSixCount", questionsLevelsCountMap.get(6));

        if(questionsLevelsCountMap.containsKey(new Integer(7)))
            modelMap.put("levelSevenCount", questionsLevelsCountMap.get(7));

//        modelMap.put("toReviewCount", gapFillingQuestionService.getTodayQuestions(user).size());


        return "index";
    }
}
