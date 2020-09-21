package spring.repository_layer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface OfferRepository extends CrudRepository<Offer,Long> {

        Offer findByCar(ConcreteCar car);
        Optional<List<Offer>> findAllByUser(User user);

        @Query("from Offer o WHERE o.user.id = :userId ")
        Optional<List<Offer>> getOffersByUserId( @Param("userId") Long userId);

        List<Offer> findAll();
        @Query("From Offer ") Optional<List<Offer>> findAllOffers();

        Optional<Offer> findById(Long id);

        @Query("From Offer o where o.user.id =:userId and o.id =:offerId")
        Optional<Offer> findByUserIdAndOfferId(@Param("userId") Long userId,@Param("offerId") Long offerId);

        @Query("SELECT o.car.car.type.carType , COUNT (o) from Offer o GROUP BY o.car.car.type.carType")
        List<Object> getDistinctTypesWithCounter();

        @Query("SELECT o.car.car.model.carMark.mark, COUNT (o) from Offer o GROUP BY o.car.car.model.carMark.mark")
        List<Object> getDistinctMarksWithCounter();

        @Query("SELECT o.car.car.model.model, COUNT (o) from Offer o WHERE o.car.car.model.carMark.mark = :mark GROUP BY o.car.car.model.model")
        List<Object> getDistinctModelsBasedOnMarkWithCounter(@Param("mark") String mark);

        @Query("SELECT o.car.location_city, COUNT (o) from Offer o  GROUP BY o.car.location_city")
        List<Object> getDistinctLocationsWithCounter();

        //query will return all cars specified by parameters
        // model mark type additional-eq  year fuel country location
        @Query("FROM Offer o WHERE " +
                "(:carType is null or o.car.car.type.carType = :carType) and " +
                "(:model is null or o.car.car.model.model = :model) and" +
                "(:mark is null or o.car.car.model.carMark.mark = :mark) and" +
                "(:productionYearFrom is null or o.car.car.production_year > :productionYearFrom) and " +
                "(:productionYearTo is null or o.car.car.production_year < :productionYearTo) and" +
                "(:state is null or o.car.state = :state) and" +
                "(:fuelType is null or o.car.fuelType = :fuelType) and" +
                "(:mileage_from is null or o.car.mileage > :mileage_from ) and" +
                "(:mileage_to is null or o.car.mileage < :mileage_to ) and" +
                "(:lowPrice is null or o.price > :lowPrice ) and" +
                "(:highPrice is null or o.price < :highPrice ) and" +
                "(:capacityFrom is null or o.car.capacity > :capacityFrom ) and" +
                "(:capacityTo is null or o.car.capacity < :capacityTo ) and" +
                "(:gearbox is null or o.car.gearbox = :gearbox ) and" +
                "(:powerFrom is null or o.car.power > :powerFrom ) and" +
                "(:powerTo is null or o.car.power < :powerTo ) " +
                "" )

        Optional<List<Offer>> findAllByParameters
       (
            @Param("carType") String carType,
            @Param("model") String model,
            @Param("mark") String mark,
            @Param("productionYearFrom") Integer productionYearFrom,
            @Param("productionYearTo") Integer productionYearTo,
            @Param("state") State state,
            @Param("fuelType") FuelType fuelType,
            @Param("mileage_from") Integer mileageFrom,
            @Param("mileage_to") Integer mileageTo,
            @Param("lowPrice") Integer lowPrice,
            @Param("highPrice") Integer highPrice,
            @Param("capacityFrom") Float capacityFrom,
            @Param("capacityTo") Float capacityTo,
            @Param("gearbox") GearBox gearbox,
            @Param("powerFrom") Integer powerFrom,
            @Param("powerTo") Integer powerTo
        );


        @Query("From Offer o where o.id in :offerIds")
        Optional<List<Offer>> getOffersList( @Param("offerIds") List<Long> offerIds);
}

