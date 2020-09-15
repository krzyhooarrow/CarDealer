package spring.repository_layer.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.repository_layer.models.OfferPopularity;

import java.util.Optional;

public interface OfferPopularityRepository extends CrudRepository<OfferPopularity,Long> {
    Optional<OfferPopularity> findById(Long id);
}
