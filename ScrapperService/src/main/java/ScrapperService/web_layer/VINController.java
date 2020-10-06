package ScrapperService.web_layer;

import ScrapperService.repository_layer.models.CarData;
import ScrapperService.service_layer.VinService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/vin")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class VINController {

    private VinService service;

    @GetMapping("/{id}")
    public CarData getVINData(@PathVariable Long id) {
        return service.getCarDataByOfferId(id);
    }
}
