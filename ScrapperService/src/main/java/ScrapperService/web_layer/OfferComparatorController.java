package ScrapperService.web_layer;

import ScrapperService.repository_layer.models.CarData;
import ScrapperService.repository_layer.models.OtomotoOffer;
import ScrapperService.service_layer.OfferComparatorService;
import ScrapperService.service_layer.VinService;
import ScrapperService.service_layer.dto.CarDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController("/comparator")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OfferComparatorController {

    private OfferComparatorService service;

    @PostMapping("/offer")
    public List<OtomotoOffer> getMatchingOffers(@RequestBody CarDTO carDTO) {
        return service.getBestMatchingOffers(carDTO, 5);
    }

    @PostMapping("/scrap")
    public boolean scrapOtomotoByMakes(@RequestBody List<String> makesList) throws IOException {
        service.scrapAllOffersFromOtomoto(makesList);
        return true;
    }

    @PostMapping("/scrap/{make}")
    public boolean scrapOtomotoByMake(@PathVariable String make) {
        service.scrapOtomotoByMakeAndModel(make);
        return true;
    }
}
