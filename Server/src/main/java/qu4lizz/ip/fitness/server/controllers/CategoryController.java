package qu4lizz.ip.fitness.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qu4lizz.ip.fitness.server.models.entities.CategoryEntity;
import qu4lizz.ip.fitness.server.models.responses.CategoryResponse;
import qu4lizz.ip.fitness.server.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository repository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    @GetMapping
    List<CategoryResponse> findAll() {
        List<CategoryEntity> entities = repository.findAll();
        return entities.stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }
}
