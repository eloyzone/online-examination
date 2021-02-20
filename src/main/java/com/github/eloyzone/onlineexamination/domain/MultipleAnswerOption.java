package com.github.eloyzone.onlineexamination.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 2/1/21, 11:11 AM.
 */
@Entity
public class MultipleAnswerOption
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{question.text.cannot.be.empty}")
    private String answerStatement;

    private boolean answer;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAnswerStatement()
    {
        return answerStatement;
    }

    public void setAnswerStatement(String answerStatement)
    {
        this.answerStatement = answerStatement;
    }

    public boolean isAnswer()
    {
        return answer;
    }

    public void setAnswer(boolean answer)
    {
        this.answer = answer;
    }
}
