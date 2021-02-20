package com.github.eloyzone.onlineexamination.domain;

import com.github.eloyzone.onlineexamination.validator.MultipleAnswerOptionsConstraint;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 2/1/21, 10:39 AM.
 */
@Entity
@Table
public class MultipleChoiceQuestion extends Question
{
    @MultipleAnswerOptionsConstraint
    @Valid
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn()
    private List<MultipleAnswerOption> multipleAnswerOptions;

    public MultipleChoiceQuestion() {
        super();
        this.multipleAnswerOptions = new ArrayList<>();
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
