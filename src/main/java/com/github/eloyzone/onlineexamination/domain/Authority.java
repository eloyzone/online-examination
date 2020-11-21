package com.github.eloyzone.onlineexamination.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 11/17/20, 8:01 PM.
 */

@Entity
@Table
public class Authority implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String authority;

    public Authority()
    {
    }

    public Authority(User user, String authority)
    {
        this.user = user;
        this.authority = authority;
    }

    @Override
    public String getAuthority()
    {
        return this.authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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
