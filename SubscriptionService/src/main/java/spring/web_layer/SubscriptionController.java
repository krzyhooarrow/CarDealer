package spring.web_layer;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.Main;
import spring.service_layer.PopularityService;
import spring.service_layer.SubscriptionsService;
import spring.service_layer.exceptions.OfferNotFoundException;
import spring.service_layer.exceptions.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/subscriptions")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class SubscriptionController {

    private SubscriptionsService service;


    @PostMapping("/subscribe/{id}")
    public ResponseEntity<String> sub(@PathVariable Long id, HttpServletRequest request) {
        service.subscribe(Long.valueOf(request.getHeader("user-id")),id);
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/unsubscribe/{id}")
    public ResponseEntity<String> unsub(@PathVariable Long id, HttpServletRequest request) throws UserNotFoundException {
        service.unsubscribe(Long.valueOf(request.getHeader("user-id")),id);
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/getSubscriptions")
    public Set<Long> getSubscriptions(HttpServletRequest request) throws UserNotFoundException {
        return service.getSubscribedOffersIDs(Long.valueOf(request.getHeader("user-id")));
    }
}
