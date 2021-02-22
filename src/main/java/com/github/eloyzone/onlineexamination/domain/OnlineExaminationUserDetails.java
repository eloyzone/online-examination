package com.github.eloyzone.onlineexamination.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 11/17/20, 7:05 PM.
 */
public class OnlineExaminationUserDetails extends User
{
    private String nickname;

    public OnlineExaminationUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities)
    {
        super(username, password, authorities);
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }
}
