package ScrapperService.service_layer;

import ScrapperService.repository_layer.OffersComparator;
import ScrapperService.repository_layer.models.OfferMatches;
import ScrapperService.repository_layer.models.OtomotoOffer;
import ScrapperService.repository_layer.repositories.OfferMatchesRepository;
import ScrapperService.repository_layer.repositories.OtomotoOfferRepository;
import ScrapperService.service_layer.dto.OfferDTO;
import ScrapperService.web_layer.exceptions.OffersNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OfferComparatorService {

    private OtomotoOfferRepository repository;
    private OfferMatchesRepository matchesRepository;
    private final static Logger logger = LoggerFactory.getLogger(OfferComparatorService.class);

    public List<OtomotoOffer> getMatchingOffers(Long offerId) throws OffersNotFoundException {
        return matchesRepository.findByOfferId(offerId).orElseThrow(OffersNotFoundException::new).getOffersList();
    }

    public void matchOffers(OfferDTO carDTO, int howMany)  {
        try {
            final float maxDistance = 0.8F;

            List<OtomotoOffer> offers = new ArrayList<>(Objects .requireNonNull(repository.getByMakeIgnoreCase(carDTO.getMark()).orElseThrow(OffersNotFoundException::new)));

            List<OtomotoOffer> mostMatchingOffers = offers.stream()
                    .filter(offer -> OffersComparator.compare(offer, carDTO) < maxDistance)
                    .limit(howMany)
                    .collect(Collectors.toList());

            matchesRepository.save(new OfferMatches(carDTO.getId(), mostMatchingOffers));
        }catch (OffersNotFoundException e){
            logger.error("Couldn't find similar offers due to "+ e.getMessage());
        }
    }
}
