package spring.repository_layer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.OfferPopularity;
import spring.repository_layer.models.UserSubscriptions;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSubscriptionsRepository extends CrudRepository<UserSubscriptions,Long> {

    Optional<List<Long>> getUserSubscriptionsById(Long id);
 }
