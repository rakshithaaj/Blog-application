package com.blogPostApp.blogserver.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.blogPostApp.blogserver.entities.User;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You can define user roles and authorities here
        // For simplicity, let's assume all users have a ROLE_USER role
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can implement custom logic for account expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // You can implement custom logic for account locking
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You can implement custom logic for credentials expiration
    }

    @Override
    public boolean isEnabled() {
        return true; // You can implement custom logic for user status
    }
}

