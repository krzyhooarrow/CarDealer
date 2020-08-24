package spring.web_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.repository_layer.db_init.RandomDataGenerator;
import spring.repository_layer.models.cars.Car;
import spring.repository_layer.repositories.CarRepository;
import spring.service_layer.dto.UserDTO;
import spring.service_layer.services.AuthService;
import spring.web_layer.exceptions.JWTAuthException;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MainController {

    @GetMapping("")
    public ResponseEntity<String> onlineVerification() throws JWTAuthException {
        return new ResponseEntity<>("Server online", OK);
    }

//    @GetMapping("/findByParams")
//    public List<Car> getAll(@RequestParam Map<String,String> params){
//
//        return null;
//    }

    @GetMapping("/randomInit")
    public ResponseEntity<String> initializeRandom(){
        RandomDataGenerator.generateRandomData(500,10);
        return new ResponseEntity<>("Data generated", OK);
    }

}
