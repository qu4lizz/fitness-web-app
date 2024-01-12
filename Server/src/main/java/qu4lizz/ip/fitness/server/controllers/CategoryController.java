package qu4lizz.ip.fitness.server.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;
import qu4lizz.ip.fitness.server.models.requests.CategorySubscribeRequest;
import qu4lizz.ip.fitness.server.models.responses.AttributeResponse;
import qu4lizz.ip.fitness.server.models.responses.CategoryResponse;
import qu4lizz.ip.fitness.server.models.responses.SubscribedCategoriesResponse;
import qu4lizz.ip.fitness.server.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @PostMapping("/subscribe")
    public void subscribeUserToCategory(@RequestBody CategorySubscribeRequest request) throws BadRequestException {
        categoryService.subscribeUserToCategory(request);
    }

    @GetMapping("/subscribed/{id}")
    public List<SubscribedCategoriesResponse> getSubscribedCategories(@PathVariable Integer id) {
        return categoryService.getSubscribedCategories(id);
    }

    @GetMapping("/attributes/{categoryId}")
    public List<AttributeResponse> getCategoryAttributes(@PathVariable Integer categoryId) {
        return categoryService.getCategoryAttributes(categoryId);
    }
}
