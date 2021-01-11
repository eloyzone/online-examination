package com.github.eloyzone.onlineexamination.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 1/5/21, 1:54 PM.
 */
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins
{
    @Id
    private String series;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @NotNull
    @Column(name = "token", nullable = false)
    private String token;

    @NotNull
    @Column(name = "last_used", nullable = false)
    private LocalDateTime lastUsed;

    public String getSeries()
    {
        return series;
    }

    public void setSeries(String series)
    {
        this.series = series;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public LocalDateTime getLastUsed()
    {
        return lastUsed;
    }

    public void setLastUsed(LocalDateTime lastUsed)
    {
        this.lastUsed = lastUsed;
    }
}
