package spring.service_layer.services;



import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.Authorities;
import spring.repository_layer.models.User;
import spring.repository_layer.repositories.UserRepository;
import spring.service_layer.dto.AuthenticationRequest;
import spring.service_layer.dto.AuthenticationResponse;
import spring.service_layer.dto.UserDTO;
import spring.service_layer.services.jwt.Encoder;
import spring.service_layer.services.jwt.JwtComponent;
import spring.service_layer.services.jwt.UserDetailsServiceProvider;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsServiceProvider userDetailsServiceProvider;
    private JwtComponent component;
    private UserRepository userRepository;
    private Encoder encoder;

    public boolean registerUser(UserDTO userDTO){
        return !userRepository.findByUsername(userDTO.getUsername()).isPresent() && createUser(userDTO);
    }

    public boolean createUser(UserDTO userDTO){
        userRepository.save(new User(userDTO.getUsername(),encoder.encode(userDTO.getPassword()),userDTO.getEmail(), Authorities.USER));
        return true;
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));

        AuthenticationResponse a = new AuthenticationResponse(
                component.generateToken(userDetailsServiceProvider.loadUserByUsername(authenticationRequest.getUsername()))
        );
        return a;
    }




}
