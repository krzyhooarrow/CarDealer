package spring.web_layer.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.services.FiltersService;
import spring.service_layer.services.TransactionService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/filters")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilterController {

    private FiltersService service;

    @GetMapping("/carType")
    public ResponseEntity<List<Object>> getDistinctCarTypes()  {
        return ResponseEntity.ok(service.getDistinctCarTypes());
    }

    @GetMapping("/marks")
    public ResponseEntity<List<Object>> getDistinctCarMarks()  {
        return ResponseEntity.ok(service.getDistinctCarMarks());
    }

    @PostMapping("/models")
    public ResponseEntity<List<Object>> getDistinctCarModelsBasedOnMark(@RequestBody String mark)  {
        return ResponseEntity.ok(service.getDistinctCarModelsBasedOnMark(mark));
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Object>> getDistinctLocations()  {
        return ResponseEntity.ok(service.getDistinctLocationCities());
    }
}
