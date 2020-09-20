package spring.repository_layer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.cars.FuelType;
import spring.repository_layer.models.cars.FuelTypeEnum;

import java.util.List;
import java.util.Optional;


@Repository
public interface FuelTypeRepository extends CrudRepository<FuelType,Long> {

    Optional<FuelType> findByFuelTypeEnum(FuelTypeEnum fuelTypeEnum);

}
