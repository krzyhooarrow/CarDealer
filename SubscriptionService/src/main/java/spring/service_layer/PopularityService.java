package spring.service_layer;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.OfferPopularity;
import spring.repository_layer.repositories.OfferPopularityRepository;
import spring.service_layer.exceptions.OfferNotFoundException;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@Scope("singleton")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class PopularityService {

    private static final Logger logger = LoggerFactory.getLogger(PopularityService.class);
    private OfferPopularityRepository offerPopularityRepository;

    public void updateOfferPopularity(Long offerID) {
        OfferPopularity o = offerPopularityRepository.findById(offerID).orElseGet(()->new OfferPopularity(offerID));
        o.setVisitsCounter(o.getVisitsCounter()+1);
        offerPopularityRepository.save(o);
    }

    public Integer getOfferPopularity(Long offerID) {
      return offerPopularityRepository.findById(offerID).orElseGet(()->newOfferPopularity(offerID)).getVisitsCounter();
    }

    private OfferPopularity newOfferPopularity(Long offerID){
        return offerPopularityRepository.save(new OfferPopularity(offerID));
    }
}
