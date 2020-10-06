package ScrapperService.service_layer;

import ScrapperService.repository_layer.models.CarData;
import ScrapperService.repository_layer.repositories.CarDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class VinService {

    private CarDataRepository carDataRepository;

    public CarData getCarDataByOfferId(Long offerId) {
        return carDataRepository.findByOfferId(offerId).orElse(null);
    }

    public boolean saveCarData(CarData carData) {
        try {
            carDataRepository.save(carData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public CarData scrapCarData(String VIN) {   return null;  }


}
