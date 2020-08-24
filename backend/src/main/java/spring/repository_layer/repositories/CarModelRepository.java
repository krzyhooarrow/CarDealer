package spring.repository_layer.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.repository_layer.models.cars.CarModel;

import java.util.List;

public interface CarModelRepository  extends CrudRepository<CarModel,Long> {

    List<CarModel> findAllByModel(String model);

}
