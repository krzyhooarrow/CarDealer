package ScrapperService.service_layer;

import ScrapperService.repository_layer.repositories.OtomotoOfferRepository;
import ScrapperService.service_layer.scrappers.OtomotoScrapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class ScrapperService {

    private OtomotoScrapper otomotoScrapper;
    private OtomotoOfferRepository repository;

    public void scrapOtomotoByMakeAndModel(String make) {
        new Thread(() -> {
            try {
                otomotoScrapper.scrapOtomotoOffersByMake(make);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void clearOtomotoOffersByMake(String make) {  repository.deleteAllByMake(make);  }

    public void scrapAllMakes() {
        new Thread(() -> {
            otomotoScrapper.scrapAllOtomotoMakes();
        }).start();
    }
}
