package spring.service_layer.services.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import spring.repository_layer.models.User;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final User user;

    public UserDetails(User user){this.user=user;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new LinkedList<>() ;
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
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
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
