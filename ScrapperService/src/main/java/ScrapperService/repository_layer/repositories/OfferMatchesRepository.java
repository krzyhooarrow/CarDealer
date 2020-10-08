package ScrapperService.repository_layer.repositories;

import ScrapperService.repository_layer.models.OfferMatches;
import ScrapperService.repository_layer.models.OtomotoOffer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferMatchesRepository  extends CrudRepository<OfferMatches,Long> {


    @Query("FROM OfferMatches o where o.offerId =:id")
    Optional<OfferMatches> findByOfferId(@Param("id") Long id);
}
