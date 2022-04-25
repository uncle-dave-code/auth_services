package com.uncledavecode.admin.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uncledavecode.admin.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String name;
    private String lastname;

    public UserDetailsImpl(Long id, String email, String name, String lastname, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getLastname(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getLastname() {
        return this.lastname;
    }

    @Override
    public String getPassword() {
        return this.password;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDetailsImpl other = (UserDetailsImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
