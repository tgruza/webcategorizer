package pl.tgruza.webcategorizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.tgruza.webcategorizer.model.Website;


public interface WebsiteRepository extends JpaRepository<Website, Long> {

    @Query(value = "SELECT id FROM Website WHERE url = ?1", nativeQuery = true)
    Long findIdByUrl(String url);

}
