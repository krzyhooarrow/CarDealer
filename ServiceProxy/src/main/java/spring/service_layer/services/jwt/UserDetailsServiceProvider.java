package spring.service_layer.services.jwt;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.repository_layer.models.User;
import spring.repository_layer.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service("userDetailsService")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserDetailsServiceProvider implements UserDetailsService {

    private UserRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = usersRepository.findByUsername(username);
         userOptional.orElseThrow(() -> new UsernameNotFoundException("No user has been found with username: " + username));
        return userOptional.map(spring.service_layer.services.jwt.UserDetails::new).get();
    }
}
