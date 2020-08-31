package spring.repository_layer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.cars.CarModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarModelRepository  extends CrudRepository<CarModel,Long> {

    Optional<CarModel> findByModel(String model);

}
