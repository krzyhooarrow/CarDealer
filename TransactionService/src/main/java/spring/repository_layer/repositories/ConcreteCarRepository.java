package spring.repository_layer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import spring.repository_layer.models.cars.Car;
import spring.repository_layer.models.cars.ConcreteCar;
import spring.repository_layer.models.cars.FuelType;

import java.util.List;
import java.util.Optional;

public interface ConcreteCarRepository extends CrudRepository<ConcreteCar,Long> {


    ConcreteCar findAllByCar(Car car);

}
