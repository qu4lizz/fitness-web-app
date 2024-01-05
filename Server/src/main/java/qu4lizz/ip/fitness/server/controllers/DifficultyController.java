package qu4lizz.ip.fitness.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qu4lizz.ip.fitness.server.models.entities.DifficultyEntity;
import qu4lizz.ip.fitness.server.repositories.DifficultyRepository;

import java.util.List;

@RestController
@RequestMapping("/api/difficulties")
public class DifficultyController {
    private final DifficultyRepository repository;

    public DifficultyController(DifficultyRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<DifficultyEntity> findAll() {
        return repository.findAll();
    }
}
