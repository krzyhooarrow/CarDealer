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

import javax.persistence.GeneratedValue;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/filters")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
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

    @GetMapping("/fuelTypes")
    public ResponseEntity<List<String>> fuelTypes()  {
        return ResponseEntity.ok(service.getDistinctFuelTypes());
    }

    @GetMapping("/additionalEquipment")
    public ResponseEntity<List<String>> getAdditionalEquipment()  {
        return ResponseEntity.ok(service.getAdditionalEquipment());
    }

    @GetMapping("/gearboxes")
    public ResponseEntity<List<String>> getGearboxTypes()  {
        return ResponseEntity.ok(service.getGearboxes());
    }

    @GetMapping("/states")
    public ResponseEntity<List<String>> getStates()  {
        return ResponseEntity.ok(service.getStates());
    }
}
