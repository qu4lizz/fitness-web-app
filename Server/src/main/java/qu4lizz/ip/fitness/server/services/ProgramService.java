package qu4lizz.ip.fitness.server.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qu4lizz.ip.fitness.server.models.dto.ProgramAttributeCreateDTO;
import qu4lizz.ip.fitness.server.models.entities.*;
import qu4lizz.ip.fitness.server.models.requests.BuyProgramRequest;
import qu4lizz.ip.fitness.server.models.requests.CommentRequest;
import qu4lizz.ip.fitness.server.models.requests.ProgramCreateRequest;
import qu4lizz.ip.fitness.server.models.responses.ProgramDataViewResponse;
import qu4lizz.ip.fitness.server.models.responses.ProgramDetailsResponse;
import qu4lizz.ip.fitness.server.repositories.*;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class ProgramService {
    private final ProgramRepository repository;
    private final UserRepository userRepository;
    private final ProgramImageRepository programImageRepository;
    private final ProgramHasAttributesRepository programHasAttributesRepository ;
    private final CommentRepository commentRepository;
    private final ParticipationRepository participationRepository;
    private final ModelMapper modelMapper;
    private final LogService logService;

    public ProgramService(ProgramRepository repository, UserRepository userRepository, ProgramImageRepository programImageRepository, ProgramHasAttributesRepository programHasAttributesRepository, CommentRepository commentRepository, ParticipationRepository participationRepository, ModelMapper mapper, LogService logService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.programImageRepository = programImageRepository;
        this.programHasAttributesRepository = programHasAttributesRepository;
        this.commentRepository = commentRepository;
        this.participationRepository = participationRepository;
        this.modelMapper = mapper;
        this.logService = logService;
    }

    public Page<ProgramDataViewResponse> findAll(String idCategory, String idDifficulty, Pageable page) {
        Page<ProgramEntity> resultPage = repository.findAll(
                findByCriteria(null, null, idCategory != null ? Integer.parseInt(idCategory) : null,
                        idDifficulty != null ? Integer.parseInt(idDifficulty) : null, Instant.now(), false), page);

        logService.log("Fetching all programs");
        return resultPage.map(e -> modelMapper.map(e, ProgramDataViewResponse.class));
    }

    public Page<ProgramDataViewResponse> findAllByUser(Integer idUser, String idCategory, String idDifficulty, Pageable page, String dateStatus) {
        boolean dateFilter = "completed".equals(dateStatus) || "upcoming".equals(dateStatus);

        Page<ProgramEntity> resultPage = repository.findAll(
                findByCriteria(null, idUser, idCategory != null ? Integer.parseInt(idCategory) : null,
                        idDifficulty != null ? Integer.parseInt(idDifficulty) : null, dateFilter ? Instant.now() : null, "completed".equals(dateStatus)), page);

        logService.log("Fetching all programs created by user with id " + idUser);

        return resultPage.map(e -> modelMapper.map(e, ProgramDataViewResponse.class));
    }

    public Page<ProgramDataViewResponse> findAllWhereUserParticipating(Integer idUser, String idCategory, String idDifficulty, Pageable page, String dateStatus) {
        boolean dateFilter = "completed".equals(dateStatus) || "upcoming".equals(dateStatus);

        List<UserParticipatesProgramEntity> participation = participationRepository.findAllByIdUser(idUser);

        List<Integer> programIds = participation.stream().map(UserParticipatesProgramEntity::getIdProgram).toList();

        Page<ProgramEntity> resultPage = repository.findAll(
                findByCriteria(programIds, null, idCategory != null ? Integer.parseInt(idCategory) : null,
                        idDifficulty != null ? Integer.parseInt(idDifficulty) : null, dateFilter ? Instant.now() : null, "completed".equals(dateStatus)), page);

        logService.log("Fetching all programs where user with id participates " + idUser);

        return resultPage.map(e -> modelMapper.map(e, ProgramDataViewResponse.class));
    }

    public ProgramDetailsResponse findById(Integer id) throws ChangeSetPersister.NotFoundException {
        ProgramEntity entity = repository.findByIdAndActive(id, true).orElseThrow(ChangeSetPersister.NotFoundException::new);

        logService.log("Fetching program with id " + id);
        return modelMapper.map(entity, ProgramDetailsResponse.class);
    }

    public static Specification<ProgramEntity> findByCriteria(List<Integer> participatedProgramIds, Integer idUser, Integer idCategory, Integer idDifficulty, Instant start, Boolean isBefore) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.isTrue(root.get("active"));

            if (participatedProgramIds != null && !participatedProgramIds.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, root.get("id").in(participatedProgramIds));
            }
            if (idUser != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("instructor").get("id"), idUser));
            }
            if (idCategory != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category").get("id"), idCategory));
            }

            if (idDifficulty != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("difficulty").get("id"), idDifficulty));
            }

            if (start != null) {
                if (isBefore) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThan(root.get("start"), start));
                } else {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThan(root.get("start"), start));
                }
            }

            return predicate;
        };
    }

    public void create(ProgramCreateRequest programRequest) {
        ProgramEntity programEntity = modelMapper.map(programRequest, ProgramEntity.class);

        UserEntity userEntity = userRepository.findById(programRequest.getIdUser())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + programRequest.getIdUser()));
        programEntity.setInstructor(userEntity);

        ProgramEntity savedProgram = repository.save(programEntity);

        logService.log("Creating new program named '" + programRequest.getName() + "'");

        saveProgramImages(savedProgram.getId(), programRequest.getProgramImages());
        saveProgramAttributes(programRequest.getAttributes(), savedProgram.getId());
    }

    private void saveProgramImages(Integer programId, MultipartFile[] images) {
        for (MultipartFile image : images) {
            ProgramImageEntity imageEntity = new ProgramImageEntity();
            imageEntity.setIdProgram(programId);
            try {
                imageEntity.setImage(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image bytes", e);
            }
            programImageRepository.save(imageEntity);
        }
    }

    private void saveProgramAttributes(Map<Integer, String> attributes, Integer idProgram) {
        for (var entry : attributes.entrySet()) {
            ProgramHasAttributesEntity entity = modelMapper.map(new ProgramAttributeCreateDTO(entry.getKey(), entry.getValue(), idProgram), ProgramHasAttributesEntity.class);

            programHasAttributesRepository.save(entity);
        }
    }

    public void delete(Integer id) throws ChangeSetPersister.NotFoundException {
        ProgramEntity programEntity = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        programEntity.setActive(false);

        logService.log("Deleting program with id " + id);

        repository.save(programEntity);
    }

    public void addComment(CommentRequest commentRequest) throws ChangeSetPersister.NotFoundException {
        repository.findById(commentRequest.getIdProgram()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        UserCommentProgramEntity commentEntity = modelMapper.map(commentRequest, UserCommentProgramEntity.class);

        logService.log("User with id " + commentRequest.getIdUser() + " commented on program with id " + commentRequest.getIdProgram());

        commentRepository.save(commentEntity);
    }

    public void buyProgram(BuyProgramRequest request) throws ChangeSetPersister.NotFoundException, BadRequestException {
        repository.findById(request.getIdProgram()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        var exists = participationRepository.findByIdProgramAndIdUser(request.getIdProgram(), request.getIdUser());
        if (exists != null)
            throw new BadRequestException("Already participating");

        UserParticipatesProgramEntity entity = modelMapper.map(request, UserParticipatesProgramEntity.class);

        logService.log("User with id " + request.getIdUser() + " bought program with id " + request.getIdProgram());

        participationRepository.save(entity);
    }

    public boolean userParticipatesProgram(Integer uid, Integer pid) {
        var exists = participationRepository.findByIdProgramAndIdUser(pid, uid);
        return exists != null;
    }
}
