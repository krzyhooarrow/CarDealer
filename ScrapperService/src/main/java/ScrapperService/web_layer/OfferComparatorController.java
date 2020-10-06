package ScrapperService.web_layer;

import ScrapperService.repository_layer.models.CarData;
import ScrapperService.repository_layer.models.OtomotoOffer;
import ScrapperService.service_layer.OfferComparatorService;
import ScrapperService.service_layer.VinService;
import ScrapperService.service_layer.dto.CarDTO;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/comparator")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OfferComparatorController {

    private OfferComparatorService service;

    @PostMapping("/offer/{id}")
    public List<OtomotoOffer> getMatchingOffers(@PathVariable Long id) {
        return service.getMatchingOffers(id);
    }
}
