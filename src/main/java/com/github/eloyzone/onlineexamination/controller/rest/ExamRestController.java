package com.github.eloyzone.onlineexamination.controller.rest;

import com.github.eloyzone.onlineexamination.domain.*;
import com.github.eloyzone.onlineexamination.service.GapFillingQuestionService;
import com.github.eloyzone.onlineexamination.service.MultipleChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 10/10/20, 6:21 PM.
 */
@RestController
@RequestMapping("/rest/exam")
public class ExamRestController
{
    @Autowired
    private GapFillingQuestionService gapFillingQuestionService;

    @Autowired
    private MultipleChoiceQuestionService multipleChoiceQuestionService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/checkAnswer")
    public ResponseEntity<?> getSearchResultViaAjax(@AuthenticationPrincipal User user,
                                                    @RequestBody QuestionCheckingRequest questionCheckingRequest)
    {
        //todo: check for user authorization

        QuestionCheckingResponse questionCheckingResponse = gapFillingQuestionService.checkQuestionAnswer(questionCheckingRequest);

        return ResponseEntity.ok(questionCheckingResponse);
    }

    @PostMapping("/checkMultipleChoiceQuestion")
    public ResponseEntity<?> getSearchResultViaAjax(@AuthenticationPrincipal User user,
                                                    @RequestBody MultipleChoiceQuestion multipleChoiceQuestion)
    {
        //todo: check for user authorization

        QuestionCheckingResponse questionCheckingResponse = multipleChoiceQuestionService.checkQuestionAnswer(multipleChoiceQuestion);

        return ResponseEntity.ok(questionCheckingResponse);
    }
}
