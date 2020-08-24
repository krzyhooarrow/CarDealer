package spring.repository_layer.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.repository_layer.models.Email;

@Repository
public interface EmailRepository extends CrudRepository<Email,Long> {
}
