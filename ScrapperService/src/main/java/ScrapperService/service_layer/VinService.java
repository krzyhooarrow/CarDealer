package ScrapperService.service_layer;

import ScrapperService.repository_layer.models.VINData;
import ScrapperService.repository_layer.repositories.VinDataRepository;
import ScrapperService.service_layer.dto.OfferDTO;
import ScrapperService.service_layer.vinAPI.VinScrapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class VinService {

    private VinDataRepository vinDataRepository;
    private static final Logger logger = LoggerFactory.getLogger(VinService.class);

    public VINData getCarDataByOfferId(Long offerId) {
        return vinDataRepository.findByOfferId(offerId).orElse(null);
    }

    public void scrapCarData(OfferDTO o) {
        try {
            vinDataRepository.save(new VINData(o.getId(), o.getVin(), new VinScrapper().getValues(o.getVin())));
        } catch (IOException e) {
            logger.error("Couldn't get vin data due to " +e.getMessage());
        }
    }


}
