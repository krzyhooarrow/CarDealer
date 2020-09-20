package spring.web_layer;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.service_layer.PopularityService;
import spring.service_layer.SubscriptionsService;
import spring.service_layer.exceptions.OfferNotFoundException;

@RestController
@RequestMapping("/popularity")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class PopularityController {

    private PopularityService popularityService;

    @PostMapping("/popularize/{id}")
    public ResponseEntity<String> popularize(@PathVariable Long id) {
        popularityService.updateOfferPopularity(id);
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping("/getPopularity/{id}")
    public Integer getOfferPopularity(@PathVariable Long id) {
        return popularityService.getOfferPopularity(id);
    }
}
