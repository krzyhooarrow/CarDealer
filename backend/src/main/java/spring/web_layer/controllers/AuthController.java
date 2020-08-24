package spring.web_layer.controllers;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.service_layer.dto.AuthenticationResponse;
import spring.service_layer.dto.AuthenticationRequest;
import spring.service_layer.dto.UserDTO;
import spring.service_layer.services.AuthService;
import spring.service_layer.services.jwt.JwtComponent;
import spring.service_layer.services.jwt.UserDetailsServiceProvider;

@AllArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
public class AuthController {

    private AuthService authService;

    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    public ResponseEntity<?> getToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        return ResponseEntity.ok(authService.authenticateUser(authenticationRequest));
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<String> registerAccount(@RequestBody UserDTO userDTO){
        return authService.registerUser(userDTO) ?
                new ResponseEntity<>("Registered successfully", HttpStatus.CREATED) :
                new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
    }


}