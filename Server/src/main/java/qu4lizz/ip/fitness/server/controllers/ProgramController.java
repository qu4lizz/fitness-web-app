package qu4lizz.ip.fitness.server.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import qu4lizz.ip.fitness.server.models.requests.ProgramCreateRequest;
import qu4lizz.ip.fitness.server.models.responses.ProgramDataViewResponse;
import qu4lizz.ip.fitness.server.services.ProgramService;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public Page<ProgramDataViewResponse> findAll(@RequestParam(name = "idCategory", required = false) String idCategory,
                                                 @RequestParam(name = "idDifficulty", required = false) String idDifficulty,
                                                 Pageable page) {

        return programService.findAll(idCategory, idDifficulty, page);
    }

    @PostMapping(consumes = "multipart/form-data")
    public void create(@ModelAttribute ProgramCreateRequest request) {
        programService.create(request);
    }

}
