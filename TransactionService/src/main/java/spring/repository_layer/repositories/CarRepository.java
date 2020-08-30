package spring.repository_layer.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.cars.*;
import spring.service_layer.dto.OfferDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car,Long> {


     List<Car> findAll();

    @Query(
            "FROM Car  WHERE " +
            "(:type is null or type = :type) and " +
            "(:model is null or model = :model) and" +
            "(:productionYear is null or production_year = :productionYear) "

    )
    Optional<Car> findAllByParameters(
            @Param("type") CarType carType,
            @Param("model") CarModel model,
            @Param("productionYear") Integer productionYear
    );
}
