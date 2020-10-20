package spring.web_layer;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.service_layer.PopularityService;
import spring.service_layer.SubscriptionsService;
import spring.service_layer.exceptions.OfferNotFoundException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/popularity")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class PopularityController {

    private PopularityService popularityService;
    private SubscriptionsService subscriptionsService;

    @PostMapping("/popularize/{id}")
    public boolean popularize(@PathVariable Long id) {
        popularityService.updateOfferPopularity(id);
        return true;
    }

    @GetMapping("/getPopularity/{id}")
    public Integer getOfferPopularity(@PathVariable Long id) {
        return popularityService.getOfferPopularity(id);
    }

    @GetMapping("/getRatio/{id}")
    public Map<String,Float> getRatios(@PathVariable Long id) {
        return Collections.singletonMap("popularity", popularityService.getOfferWatchersRatio(id));
    }
}
