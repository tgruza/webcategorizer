package pl.tgruza.webcategorizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tgruza.webcategorizer.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
