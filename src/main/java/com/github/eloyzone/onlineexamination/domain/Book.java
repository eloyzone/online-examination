package com.github.eloyzone.onlineexamination.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 9/16/20, 12:03 PM.
 */
@Entity
@Table
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
    private List<GapFillingQuestion> gapFillingQuestions = new ArrayList<>();

    @NotEmpty(message = "{book.name.cannot.be.empty}")
    private String name;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public List<GapFillingQuestion> getGapFillingQuestions()
    {
        return gapFillingQuestions;
    }

    public void setGapFillingQuestions(List<GapFillingQuestion> gapFillingQuestions)
    {
        this.gapFillingQuestions = gapFillingQuestions;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}