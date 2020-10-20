package ScrapperService.repository_layer.repositories;

import ScrapperService.repository_layer.models.VINData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VinDataRepository extends CrudRepository<VINData,Long> {

    Optional<VINData> findByOfferId(Long id);

}
