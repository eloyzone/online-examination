package com.github.eloyzone.onlineexamination.domain;

import java.util.List;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 10/10/20, 6:53 PM.
 */
public class QuestionCheckingResponse
{
    public final static String CORRECT = "correct";
    public final static String WRONG = "wrong";
    public final static String ERROR = "error";

    private String status;
    private String answer;
    private String description;
    private List<MultipleAnswerOption> multipleAnswerOptions;
    private String errorMessage;


    public QuestionCheckingResponse (MultipleChoiceQuestion multipleChoiceQuestion, String status)
    {
        this.status = status;
        this.answer = "";
        this.description = multipleChoiceQuestion.getDescription();
        this.multipleAnswerOptions = multipleChoiceQuestion.getMultipleAnswerOptions();
        this.errorMessage = "";

    }
    public QuestionCheckingResponse(String status, String answer, String description)
    {
        this.status = status;
        this.answer = answer;
        this.description = description;
        this.errorMessage = "";
    }

    public QuestionCheckingResponse(String errorMessage)
    {
        this.status = this.ERROR;
        this.description = "";
        this.answer = "";
        this.errorMessage = errorMessage;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public List<MultipleAnswerOption> getMultipleAnswerOptions()
    {
        return multipleAnswerOptions;
    }

    public void setMultipleAnswerOptions(List<MultipleAnswerOption> multipleAnswerOptions)
    {
        this.multipleAnswerOptions = multipleAnswerOptions;
    }
}
