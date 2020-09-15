package spring.web_layer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.service_layer.PopularityService;
import spring.service_layer.SubscriptionsService;
import spring.service_layer.exceptions.OfferNotFoundException;
import spring.service_layer.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/carService")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OffersController {

    private SubscriptionsService service;
    private PopularityService popularityService;

    @PostMapping("/subscribe/{id}")
    public ResponseEntity<String> sub(@PathVariable Long id,@RequestBody Long user) throws UserNotFoundException {
        service.subscribe(user,id);
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/unsubscribe/{id}")
    public ResponseEntity<String> unsub(@PathVariable Long id, @RequestBody Long user) throws UserNotFoundException {
        service.unsubscribe(user,id);
        return  ResponseEntity.ok().body("OK");
    }

    @PostMapping("/getSubscriptions")
    public Set<Long> unsub(@RequestBody Long user) throws UserNotFoundException {
        return service.getSubscribedOffersIDs(user);
    }

    @PostMapping("/popularize/{id}")
    public ResponseEntity<String> popularize(@PathVariable Long id) throws OfferNotFoundException {
        popularityService.updateOfferPopularity(id);
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping("/getPopularity/{id}")
    public Integer getOfferPopularity(@PathVariable Long id) throws OfferNotFoundException {
        return popularityService.getOfferPopularity(id);
    }
}
