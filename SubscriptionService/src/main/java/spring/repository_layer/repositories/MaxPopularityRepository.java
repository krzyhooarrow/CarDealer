package spring.repository_layer.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.repository_layer.models.MaxPopularityCounter;
import spring.repository_layer.models.Type;

import java.util.Optional;

public interface MaxPopularityRepository extends CrudRepository<MaxPopularityCounter,Long> {

    Optional<MaxPopularityCounter> findByType(Type type);
}
