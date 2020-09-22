package spring.web_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.repository_layer.db_init.RandomDataGenerator;
import spring.repository_layer.models.cars.Equipment;


import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MainController {

    @GetMapping("")
    public ResponseEntity<String> onlineVerification() {
        return new ResponseEntity<>("Server online", OK);
    }
    
}
