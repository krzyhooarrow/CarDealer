package spring.service_layer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.MaxPopularityCounter;
import spring.repository_layer.models.OfferPopularity;
import spring.repository_layer.models.Type;
import spring.repository_layer.repositories.MaxPopularityRepository;
import spring.service_layer.exceptions.OfferNotFoundException;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MaxPopularityService {
    private MaxPopularityRepository popularityRepository;

    public void updateMaxPopularity(Long offerId, int offerPopularity, Type type) {
        Optional<MaxPopularityCounter> counter;
        if ((counter = popularityRepository.findByType(type)).isPresent() && counter.get().getCounter() < offerPopularity) {
            popularityRepository.delete(counter.get());
            popularityRepository.save(new MaxPopularityCounter(offerId, offerPopularity, type));
        } else if (!counter.isPresent())
            popularityRepository.save(new MaxPopularityCounter(offerId, offerPopularity, type));
    }

    public Optional<MaxPopularityCounter> getMaxPopularity(Type type){
        return popularityRepository.findByType(type);
    }
}
