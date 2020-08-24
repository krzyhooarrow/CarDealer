package spring.repository_layer.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.cars.*;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car,Long> {

     List<Car> findAll();

     //query will return all cars specified by parameters
    // model mark type additional-eq  year fuel country location
    @Query(
            "FROM Car  WHERE " +
            "(:type is null or type = :type) and " +
            "(:mark is null or mark = :mark) and " +
            "(:model is null or model = :model) and" +
            "(:additionalEq is null or additionalEquipment IN :additionalEq) and" +
            "(:productionYear is null or production_year = :productionYear) and" +
            "(:fuelType is null or fuelType = :fuelType) and" +
            "(:productionCountry is null or country = :productionCountry) and" +
            "(:locationCountry is null or location_country = :locationCountry) and" +
            "(:locationCity is null or location_city = :locationCity)"
    )
    List<Car> findAllByParameters(
            @Param("type") CarType carType,
            @Param("mark") CarMark mark,
            @Param("additionalEq") List<String> additionalEq,
            @Param("model") CarModel model,
            @Param("productionYear") Integer productionYear,
            @Param("fuelType") FuelType fuelType,
            @Param("productionCountry") String productionCountry,
            @Param("locationCountry") String locationCountry,
            @Param("locationCity") String locationCity
    );


}
