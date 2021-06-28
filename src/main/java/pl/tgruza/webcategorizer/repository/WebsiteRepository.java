package pl.tgruza.webcategorizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tgruza.webcategorizer.model.Website;


public interface WebsiteRepository extends JpaRepository<Website, Long> {

    Website findWebsiteByUrl(String url);

    boolean existsByUrl(String url);

}
