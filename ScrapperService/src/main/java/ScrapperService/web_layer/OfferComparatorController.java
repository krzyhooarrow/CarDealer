package ScrapperService.web_layer;

import ScrapperService.repository_layer.models.OtomotoOffer;
import ScrapperService.service_layer.OfferComparatorService;
import ScrapperService.web_layer.exceptions.OffersNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comparator")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OfferComparatorController {

    private OfferComparatorService service;

    @GetMapping("/offer/{id}")
    public List<OtomotoOffer> getMatchingOffers(@PathVariable Long id) throws OffersNotFoundException {
        return service.getMatchingOffers(id);
    }

}
