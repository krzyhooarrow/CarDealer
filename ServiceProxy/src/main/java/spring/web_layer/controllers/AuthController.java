package spring.web_layer.controllers;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.Main;
import spring.repository_layer.models.User;
import spring.service_layer.dto.AuthenticationRequest;
import spring.service_layer.dto.UserDTO;
import spring.service_layer.services.AuthService;

@AllArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
public class AuthController {

    private AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

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
