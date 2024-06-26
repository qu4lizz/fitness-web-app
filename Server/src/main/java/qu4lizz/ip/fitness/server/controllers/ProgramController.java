package qu4lizz.ip.fitness.server.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import qu4lizz.ip.fitness.server.models.requests.BuyProgramRequest;
import qu4lizz.ip.fitness.server.models.requests.CommentRequest;
import qu4lizz.ip.fitness.server.models.requests.ProgramCreateRequest;
import qu4lizz.ip.fitness.server.models.responses.ProgramDataViewResponse;
import qu4lizz.ip.fitness.server.models.responses.ProgramDetailsResponse;
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

    @GetMapping("/{id}")
    public ProgramDetailsResponse findById(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        return programService.findById(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public void create(@ModelAttribute ProgramCreateRequest request) {
        programService.create(request);
    }

    @GetMapping("/my")
    public Page<ProgramDataViewResponse> findAllByUser(@RequestParam(name = "idCategory", required = false) String idCategory,
                                                 @RequestParam(name = "idDifficulty", required = false) String idDifficulty,
                                                 @RequestParam(name = "dateStatus", required = false) String dateStatus,
                                                 @RequestParam(name = "idUser") String idUser,
                                                 Pageable page) {

        return programService.findAllByUser(Integer.parseInt(idUser), idCategory, idDifficulty, page, dateStatus);
    }

    @GetMapping("/participation")
    public Page<ProgramDataViewResponse> findAllWhereUserParticipating(@RequestParam(name = "idCategory", required = false) String idCategory,
                                                                       @RequestParam(name = "idDifficulty", required = false) String idDifficulty,
                                                                       @RequestParam(name = "dateStatus", required = false) String dateStatus,
                                                                       @RequestParam(name = "idUser") String idUser,
                                                                       Pageable page) {

        return programService.findAllWhereUserParticipating(Integer.parseInt(idUser), idCategory, idDifficulty, page, dateStatus);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        programService.delete(id);
    }

    @PostMapping("/comments")
    public void addComment(@RequestBody CommentRequest commentRequest) throws ChangeSetPersister.NotFoundException {
        programService.addComment(commentRequest);
    }

    @PostMapping("/buy")
    public void buyProgram(@RequestBody BuyProgramRequest request) throws ChangeSetPersister.NotFoundException, BadRequestException {
        programService.buyProgram(request);
    }

    @GetMapping("/user/{uid}/participates/{pid}")
    public boolean userParticipatesProgram(@PathVariable Integer uid, @PathVariable Integer pid) {
        return programService.userParticipatesProgram(uid, pid);
    }
}
