package com.github.eloyzone.onlineexamination.controller.rest;

import com.github.eloyzone.onlineexamination.domain.GapFillingQuestion;
import com.github.eloyzone.onlineexamination.domain.QuestionCheckingRequest;
import com.github.eloyzone.onlineexamination.domain.QuestionCheckingResponse;
import com.github.eloyzone.onlineexamination.domain.User;
import com.github.eloyzone.onlineexamination.service.GapFillingQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/10/20, 6:21 PM.
 */
@RestController
@RequestMapping("/rest/exam")
public class ExamRestController
{

    @Autowired
    private GapFillingQuestionService gapFillingQuestionService;


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/checkAnswer")
    public ResponseEntity<?> getSearchResultViaAjax(@AuthenticationPrincipal User user, @RequestBody QuestionCheckingRequest questionCheckingRequest)
    {
        //todo: check for user authorization

        QuestionCheckingResponse questionCheckingResponse = gapFillingQuestionService.checkQuestionAnswer(questionCheckingRequest);

        return ResponseEntity.ok(questionCheckingResponse);
    }
}
