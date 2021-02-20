package com.github.eloyzone.onlineexamination.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 2/1/21, 7:36 PM.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Question
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Book book;

    //todo: change its name to "Stem" or "question stem"
    @NotEmpty(message = "{question.text.cannot.be.empty}")
    private String text; // questions's text
    private String hint;

    @Lob // equals to LONGTEXT in mysql
    private String description;

    @NotNull
    private int level;

    private LocalDate createdDate;
    private LocalDate lastReviewDate;

    public Question() {
        super();
        this.level = 1;
    }

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
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
}
