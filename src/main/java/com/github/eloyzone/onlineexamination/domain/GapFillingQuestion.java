package com.github.eloyzone.onlineexamination.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 9/15/20, 5:19 PM.
 */
@Entity
public class GapFillingQuestion extends Question
{
    //todo: if encounter with long text exception, show a message to user. for instance text, answer, and hint cannot be more than 255 characters
    @NotEmpty(message = "{question.answer.cannot.be.empty}")
    private String answer;

    public GapFillingQuestion()
    {
        super();
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
}
