package com.github.eloyzone.onlineexamination.security;

import com.github.eloyzone.onlineexamination.domain.Authority;
import com.github.eloyzone.onlineexamination.domain.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 11/17/20, 7:58 PM.
 */
public class UserDetails extends User implements org.springframework.security.core.userdetails.UserDetails
{
    public UserDetails(User user)
    {
        super(user.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getAuthorities(), user.getStartingRoundLocalDate());
    }

    @Override
    public List<Authority> getAuthorities()
    {
        return super.getAuthorities();
    }

    @Override
    public String getPassword()
    {
        return super.getPassword();
    }

    @Override
    public String getUsername()
    {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
