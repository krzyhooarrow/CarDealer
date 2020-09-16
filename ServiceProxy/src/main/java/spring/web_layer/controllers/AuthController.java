package spring.web_layer.controllers;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.Main;
import spring.repository_layer.models.User;
import spring.service_layer.dto.AuthenticationRequest;
import spring.service_layer.dto.UserDTO;
import spring.service_layer.services.AuthService;
import spring.web_layer.exceptions.JWTAuthException;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("")
    public ResponseEntity<String> onlineVerification() throws JWTAuthException {
        return new ResponseEntity<>("", OK);
    }

}
