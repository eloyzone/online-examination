package com.github.eloyzone.onlineexamination.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 9/15/20, 5:19 PM.
 */
@Entity
@Table
public class GapFillingQuestion
{
    //todo: if encounter with long text exception, show a message to user. for instance text, answer, and hint cannot be more than 255 characters


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Book book;

    @NotEmpty(message = "{question.text.cannot.be.empty}")
    private String text; // questions's text
    private String hint;
    @NotEmpty(message = "{question.answer.cannot.be.empty}")
    private String answer;

    @Lob // equals to LONGTEXT in mysql
    private String description;

    @NotNull
    private int level = 1;

    private LocalDate createdDate;
    private LocalDate lastReviewDate;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getHint()
    {
        return hint;
    }

    public void setHint(String hint)
    {
        this.hint = hint;
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

    public LocalDate getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate)
    {
        this.createdDate = createdDate;
    }

    public LocalDate getLastReviewDate()
    {
        return lastReviewDate;
    }

    public void setLastReviewDate(LocalDate lastReviewDate)
    {
        this.lastReviewDate = lastReviewDate;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
}
