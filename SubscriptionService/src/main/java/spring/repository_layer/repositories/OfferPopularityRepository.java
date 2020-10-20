package spring.repository_layer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.OfferPopularity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferPopularityRepository extends CrudRepository<OfferPopularity,Long> {
    Optional<OfferPopularity> findById(Long id);

}
