package qu4lizz.ip.fitness.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qu4lizz.ip.fitness.server.models.entities.CategoryEntity;
import qu4lizz.ip.fitness.server.repositories.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }
}
