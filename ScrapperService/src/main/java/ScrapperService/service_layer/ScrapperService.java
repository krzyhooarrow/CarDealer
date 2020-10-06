package ScrapperService.service_layer;

import ScrapperService.repository_layer.OffersComparator;
import ScrapperService.repository_layer.models.OfferMatches;
import ScrapperService.repository_layer.models.OtomotoOffer;
import ScrapperService.repository_layer.models.OtomotoScrappedPage;
import ScrapperService.repository_layer.repositories.OfferMatchesRepository;
import ScrapperService.repository_layer.repositories.OtomotoOfferRepository;
import ScrapperService.repository_layer.repositories.ScrappedPagesRepository;
import ScrapperService.service_layer.dto.CarDTO;
import ScrapperService.service_layer.scrappers.OtomotoPageScrapper;
import ScrapperService.service_layer.scrappers.OtomotoScrapper;
import lombok.AllArgsConstructor;
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
public class ScrapperService {

    private OtomotoScrapper otomotoScrapper;
    public void scrapOtomotoByMakeAndModel(String make) {
        new Thread(() -> {
            try {
                otomotoScrapper.scrapOtomotoOffersByMake(make);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
