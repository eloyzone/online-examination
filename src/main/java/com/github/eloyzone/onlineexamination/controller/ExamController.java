package com.github.eloyzone.onlineexamination.controller;

import com.github.eloyzone.onlineexamination.domain.*;
import com.github.eloyzone.onlineexamination.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
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
    private QuestionService questionService;

    @GetMapping("")
    public String getExamView(@AuthenticationPrincipal User user, ModelMap modelMap)
    {
        List<Question> questions = questionService.getTodayQuestions(user);
        Collections.shuffle(questions);
        modelMap.put("questions", questions);
        return "exam";
    }
}
