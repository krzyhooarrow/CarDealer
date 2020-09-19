package spring.repository_layer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.History;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends CrudRepository<History,Long> {


    Optional<List<History>> findAllByUserId(Long userId);

}
