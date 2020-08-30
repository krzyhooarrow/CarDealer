package spring.repository_layer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.cars.CarMark;

import java.util.Optional;

@Repository
public interface CarMarkRepository extends CrudRepository<CarMark,Long> {

    Optional<CarMark> findByMark(String mark);


}
