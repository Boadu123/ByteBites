package com.example.eureka.security;

import com.example.eureka.models.UserModels;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * A custom implementation of {@link UserDetails} that wraps the application's {@link UserModels} entity.
 * <p>
 * This class is used by Spring Security to authenticate and authorize users based on the
 * user details provided in the application's domain model.
 */
public class CustomUserDetails implements UserDetails {

    private final UserModels userModels;

    /**
     * Constructs a CustomUserDetails instance by wrapping a {@link UserModels} entity.
     *
     * @param userModels the domain user to wrap
     */
    public CustomUserDetails(UserModels userModels) {
        this.userModels = userModels;
    }

    /**
     * Returns the authorities granted to the user. This implementation provides a single role
     * extracted from the user's enum-based role and prepends it with "ROLE_".
     *
     * @return a collection of {@link GrantedAuthority} objects representing user roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = userModels.getRoles().name().replace("ROLE_", "");
        return List.of(new SimpleGrantedAuthority("ROLE_" + roleName));
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the user's encoded password
     */
    @Override
    public String getPassword() {
        return userModels.getPassword();
    }

    /**
     * Returns the username used to authenticate the user.
     * In this case, the username is the user's email.
     *
     * @return the user's email
     */
    @Override
    public String getUsername() {
        return userModels.getEmail();
    }

    /**
     * Indicates whether the user's account has expired.
     * Always returns {@code true} as account expiration is not implemented.
     *
     * @return {@code true}
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * Always returns {@code true} as account locking is not implemented.
     *
     * @return {@code true}
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     * Always returns {@code true} as credential expiration is not implemented.
     *
     * @return {@code true}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * Always returns {@code true} as user disabling is not implemented.
     *
     * @return {@code true}
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the original {@link UserModels} entity wrapped by this class.
     * Useful when accessing domain-specific fields outside of Spring Security.
     *
     * @return the wrapped {@link UserModels} entity
     */
    public UserModels getUser() {
        return userModels;
    }
}

