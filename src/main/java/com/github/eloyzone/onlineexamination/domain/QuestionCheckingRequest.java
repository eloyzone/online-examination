package com.github.eloyzone.onlineexamination.domain;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/10/20, 6:53 PM.
 */
public class QuestionCheckingRequest
{
    private Long questionId;
    private String enteredAnswer;

    public Long getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(Long questionId)
    {
        this.questionId = questionId;
    }

    public String getEnteredAnswer()
    {
        return enteredAnswer;
    }

    public void setEnteredAnswer(String enteredAnswer)
    {
        this.enteredAnswer = enteredAnswer;
    }
}
