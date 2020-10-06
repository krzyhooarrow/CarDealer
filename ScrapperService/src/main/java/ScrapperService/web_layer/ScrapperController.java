package ScrapperService.web_layer;

import ScrapperService.service_layer.OfferComparatorService;
import ScrapperService.service_layer.ScrapperService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/scrapper")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class ScrapperController {

    private ScrapperService service;

    @PostMapping("/scrap/{make}")
    public boolean scrapOtomotoByMake(@PathVariable String make) {
        service.scrapOtomotoByMakeAndModel(make);
        return true;
    }
}
