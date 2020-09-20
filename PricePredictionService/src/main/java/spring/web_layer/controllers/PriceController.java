package spring.web_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.service_layer.PricePredictionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pricing")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class PriceController {

    private PricePredictionService pricePredictionService;

    @GetMapping("/{id}}")
    public ResponseEntity<Map<Integer,Integer>> getPredictedPrices(@PathVariable Long id){
        return ResponseEntity.ok().body(pricePredictionService.getPredictedPrices(id));
    }
}
