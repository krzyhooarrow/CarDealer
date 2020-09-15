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

        List<Offer> findAll();
        @Query("From Offer ") Optional<List<Offer>> findAllOffers();


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
                "(o.price BETWEEN :lowPrice and :highPrice) and" +
                "(:fuelType is null or o.car.fuelType.fuelTypeEnum = :fuelType) and" +
                "(:mark is null or o.car.car.model.carMark.mark = :mark) and" +
                "(:productionCountry is null or o.car.country = :productionCountry) and" +
                "(:locationCountry is null or o.car.location_country = :locationCountry) and" +
                "(:locationCity is null or o.car.location_city = :locationCity) and" +
                "(:carType is null or o.car.car.type.carType = :carType) and " +
                "(:model is null or o.car.car.model.model = :model) and" +
                "(:productionYear is null or o.car.car.production_year = :productionYear) " +
                "" )

        Optional<List<Offer>> findAllByParameters
       (
            @Param("carType") String carType,
            @Param("model") String model,
            @Param("mark") String mark,
            @Param("productionYear") Integer productionYear,
            @Param("fuelType") FuelTypeEnum fuelType,
            @Param("productionCountry") String productionCountry,
            @Param("locationCountry") String locationCountry,
            @Param("locationCity") String locationCity,
            @Param("lowPrice") Integer lowPrice,
            @Param("highPrice") Integer highPrice
        );
}

