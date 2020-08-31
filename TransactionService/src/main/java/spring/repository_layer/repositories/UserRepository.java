package spring.repository_layer.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByUsername(String username);
    User findByOffers(Offer offer);

}
