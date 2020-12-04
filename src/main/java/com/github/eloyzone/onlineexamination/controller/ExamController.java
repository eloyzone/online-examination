package com.github.eloyzone.onlineexamination.controller;

import com.github.eloyzone.onlineexamination.domain.GapFillingQuestion;
import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.service.GapFillingQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/10/20, 7:41 AM.
 */
@Controller
@RequestMapping("/exam")
public class ExamController
{
    @Autowired
    private GapFillingQuestionService gapFillingQuestionService;


    @GetMapping("")
    public String getExamView(@AuthenticationPrincipal User user, ModelMap modelMap)
    {
        List<GapFillingQuestion> gapFillingQuestions = gapFillingQuestionService.getTodayQuestions(user);
        modelMap.put("gapFillingQuestions", gapFillingQuestions);
        return "exam";
    }


}
