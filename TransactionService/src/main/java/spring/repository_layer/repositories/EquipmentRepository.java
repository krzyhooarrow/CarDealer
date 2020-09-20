package spring.repository_layer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.cars.Equipment;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment,Long> {

    @Query("SELECT DISTINCT e.name FROM Equipment e")
    Optional<List<Equipment>> getDistinctEquipmentNames();

    @Query("FROM Equipment e WHERE e.name IN :list")
    Optional<List<Equipment>> findByList(@Param("list")List<String> eq);
}
