package spring.repository_layer.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.repository_layer.models.cars.CarType;

public interface CarTypeRepository extends CrudRepository<CarType,Long> {
}
