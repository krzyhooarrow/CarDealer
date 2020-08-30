package spring.web_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.web_layer.exceptions.JWTAuthException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MainController {

    @GetMapping("")
    public ResponseEntity<String> onlineVerification() throws JWTAuthException {
        return new ResponseEntity<>("Server online", OK);
    }

}
