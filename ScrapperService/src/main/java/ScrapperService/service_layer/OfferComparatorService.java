package ScrapperService.service_layer;

import ScrapperService.repository_layer.OffersComparator;
import ScrapperService.repository_layer.models.OtomotoOffer;
import ScrapperService.repository_layer.repositories.OtomotoOfferRepository;
import ScrapperService.service_layer.dto.CarDTO;
import ScrapperService.service_layer.scrappers.OtomotoScrapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OfferComparatorService {

    private OtomotoScrapper otomotoScrapper;
    private OtomotoOfferRepository repository;

    public void scrapOtomotoByMakeAndModel(String make) {
        new Thread(() -> {
            try {
                repository.saveAll(otomotoScrapper.scrapOtomotoOffersByMake(make));
            } catch (IOException ignored) {}
        }).start();
    }

    public void scrapAllOffersFromOtomoto(List<String> makesList) throws IOException {
        new Thread(() -> {
            try {
                repository.saveAll(otomotoScrapper.scrapAllOffersFromOtomoto(makesList));
            } catch (IOException ignored) {}
        }).start();

    }

    public List<OtomotoOffer> getBestMatchingOffers(CarDTO carDTO, int howMany) {
        List<OtomotoOffer> mostMatchingOffers = new LinkedList<>();

        Stream<OtomotoOffer> offers = Objects
                .requireNonNull(repository.getAllOffers(carDTO.getMake(), carDTO.getModel())
                .orElse(null))
                .stream();

        for (int i = 0 ; i < howMany ; i ++)
        mostMatchingOffers.add(offers
                .filter(offer -> !mostMatchingOffers.contains(offer))
                .max(Comparator.comparing(offer ->OffersComparator.compare(offer,carDTO)))
                .get());

        return mostMatchingOffers;
    }

}
