package pl.tgruza.webcategorizer.service;

import org.springframework.stereotype.Service;
import pl.tgruza.webcategorizer.model.Category;
import pl.tgruza.webcategorizer.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getByCategory(String name) { return categoryRepository.findByName(name); }

}
