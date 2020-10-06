package ScrapperService.repository_layer.repositories;

import ScrapperService.repository_layer.models.OtomotoScrappedPage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScrappedPagesRepository extends CrudRepository<OtomotoScrappedPage,String> {

    @Query("SELECT o.numberOfLastScrappedPage from OtomotoScrappedPage o where o.make=:make")
    Optional<Integer> getLastScrappedPageByMake(@Param("make") String make);

    Optional<OtomotoScrappedPage> getByMake(String make);
}
