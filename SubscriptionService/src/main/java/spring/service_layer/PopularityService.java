package spring.service_layer;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.MaxPopularityCounter;
import spring.repository_layer.models.OfferPopularity;
import spring.repository_layer.models.Type;
import spring.repository_layer.repositories.MaxPopularityRepository;
import spring.repository_layer.repositories.OfferPopularityRepository;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.exceptions.OfferNotFoundException;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Scope("singleton")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class PopularityService {

    private static final Logger logger = LoggerFactory.getLogger(PopularityService.class);
    private OfferPopularityRepository offerPopularityRepository;
    private MaxPopularityService popularityService;

    public void updateOfferPopularity(Long offerID) {
        try {
            OfferPopularity o = offerPopularityRepository.findById(offerID).orElseThrow(OfferNotFoundException::new);
            o.setVisitsCounter(o.getVisitsCounter() + 1);
            o = offerPopularityRepository.save(o);
            popularityService.updateMaxPopularity(offerID,o.getVisitsCounter(),Type.POPULARITY);
        } catch (OfferNotFoundException exc) {
            logger.error("Offer not found :" + offerID);
        }
    }

    public Integer getOfferPopularity(Long offerID) {
        try {
            return offerPopularityRepository.findById(offerID).orElseThrow(OfferNotFoundException::new).getVisitsCounter();
        } catch (OfferNotFoundException exc) {
            logger.error("Offer not found :" + offerID);
            return 0;
        }
    }

    public void createNewOfferPopularity(OfferDTO o) {
        offerPopularityRepository.save(new OfferPopularity(o.getId()));
    }

    public float getOfferWatchersRatio(Long offerId) {
        try {
            return ((float) (offerPopularityRepository.findById(offerId).orElseThrow(OfferNotFoundException::new)).getVisitsCounter() /
                    (float) popularityService.getMaxPopularity(Type.POPULARITY).orElseThrow(OfferNotFoundException::new).getCounter());

        } catch (OfferNotFoundException e) {
            logger.error("Offer not found :" + offerId);
        }
        return 0;
    }

}
