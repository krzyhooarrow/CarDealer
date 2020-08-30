package spring.service_layer.dto;

import org.springframework.security.core.GrantedAuthority;

public class AuthoritiesDTO implements GrantedAuthority {


    @Override
    public String getAuthority() {
        return null;
    }
}
