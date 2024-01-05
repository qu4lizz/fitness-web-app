package qu4lizz.ip.fitness.server.controllers;

import org.springframework.web.bind.annotation.*;
import qu4lizz.ip.fitness.server.models.entities.ProgramEntity;
import qu4lizz.ip.fitness.server.models.requests.ProgramCreateRequest;
import qu4lizz.ip.fitness.server.services.ProgramService;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public List<ProgramEntity> findAll() {
        return programService.findAll();
    }

    @PostMapping(consumes = "multipart/form-data")
    public void create(@ModelAttribute ProgramCreateRequest request) {
        programService.create(request);
    }

}
