package spring.repository_layer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.cars.CarType;

import java.util.Optional;

@Repository
public interface CarTypeRepository extends CrudRepository<CarType,Long> {

    Optional<CarType> findByCarType(String carType);

}
