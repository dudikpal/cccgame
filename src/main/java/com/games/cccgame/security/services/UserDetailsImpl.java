package com.games.cccgame.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.games.cccgame.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String userId;

    private String username;

    private String email;

    private String garageId;

    @JsonIgnore
    private String password;

    private Collection <? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String userId, String username, String email, String garageId, String password,
                           Collection <? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.garageId = garageId;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List <GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());

        return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getGarageId(),
            user.getPassword(),
            authorities);
    }

    @Override
    public Collection <? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getGarageId() {
        return garageId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public String toString() {
        return "UserDetailsImpl{" +
            "userId='" + userId + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", garageId='" + garageId + '\'' +
            ", password='" + password + '\'' +
            ", authorities=" + authorities +
            '}';
    }
}
