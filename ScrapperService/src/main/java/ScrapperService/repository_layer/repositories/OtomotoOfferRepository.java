package ScrapperService.repository_layer.repositories;

import ScrapperService.repository_layer.models.OtomotoOffer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OtomotoOfferRepository extends CrudRepository<OtomotoOffer,Long> {

    @Query("FROM OtomotoOffer o WHERE o.make =:make AND o.model =:model")
    Optional<List<OtomotoOffer>> getAllOffersByMakeAndModel(
            @Param("make") String make,
            @Param("model") String model
    );

    void deleteAllByMake(String make);

    @Query("FROM OtomotoOffer o WHERE o.make =:make")
    Optional<List<OtomotoOffer>> getAllOffersByMake(@Param("make") String make);

    Optional<List<OtomotoOffer>> findFirst1000ByMakeIgnoreCase(String make);

    Optional<List<OtomotoOffer>> getByMakeIgnoreCase(String make);

}
