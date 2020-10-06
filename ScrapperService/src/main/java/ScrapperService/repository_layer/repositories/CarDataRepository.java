package ScrapperService.repository_layer.repositories;

import ScrapperService.repository_layer.models.CarData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarDataRepository extends CrudRepository<CarData,Long> {

    Optional<CarData> findByOfferId(Long offerId);

}
