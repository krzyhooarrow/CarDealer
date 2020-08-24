package spring.repository_layer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.Car;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer,Long> {

        Offer findByCar(Car car);
        List<Offer> findAllByUser(User user);

}
