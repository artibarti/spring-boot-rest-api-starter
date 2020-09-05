package spring.boot.rest.api.starter.web.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import spring.boot.rest.api.starter.model.User;

public class UserDetailsImpl implements UserDetails {

    private final long id;

    private final String username;

    private final String password;

    private final Set<GrantedAuthority> authorities;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    public UserDetailsImpl(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getEncodedPassword();
        this.enabled = true;
        this.credentialsNonExpired = true;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.authorities = Collections.emptySet();
    }

    public long getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}