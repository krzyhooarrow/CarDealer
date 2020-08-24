package spring.repository_layer.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.repository_layer.models.cars.CarMark;

public interface CarMarkRepository extends CrudRepository<CarMark,Long> {
}
