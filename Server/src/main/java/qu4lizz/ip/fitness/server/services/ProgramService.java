package qu4lizz.ip.fitness.server.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qu4lizz.ip.fitness.server.config.converters.ProgramImageListConverter;
import qu4lizz.ip.fitness.server.models.entities.*;
import qu4lizz.ip.fitness.server.models.requests.ProgramCreateRequest;
import qu4lizz.ip.fitness.server.models.responses.ProgramDataViewResponse;
import qu4lizz.ip.fitness.server.repositories.*;

import java.io.IOException;
import java.time.Instant;

@Service
public class ProgramService {
    private final ProgramRepository repository;
    private final UserRepository userRepository;
    private final ProgramImageRepository programImageRepository;
    private final ModelMapper modelMapper;

    public ProgramService(ProgramRepository repository, UserRepository userRepository, ProgramImageRepository programImageRepository, ModelMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.programImageRepository = programImageRepository;
        this.modelMapper = mapper;
    }

    public Page<ProgramDataViewResponse> findAll(String idCategory, String idDifficulty, Pageable page) {
        Page<ProgramEntity> resultPage = repository.findAll(
                findByCriteria(null, idCategory != null ? Integer.parseInt(idCategory) : null,
                        idDifficulty != null ? Integer.parseInt(idDifficulty) : null, Instant.now(), false), page);

        return resultPage.map(e -> modelMapper.map(e, ProgramDataViewResponse.class));
    }

    public Page<ProgramDataViewResponse> findAllByUser(Integer idUser, String idCategory, String idDifficulty, Pageable page, String dateStatus) {
        boolean dateFilter = "completed".equals(dateStatus) || "upcoming".equals(dateStatus);

        Page<ProgramEntity> resultPage = repository.findAll(
                findByCriteria(idUser, idCategory != null ? Integer.parseInt(idCategory) : null,
                        idDifficulty != null ? Integer.parseInt(idDifficulty) : null, dateFilter ? Instant.now() : null, "completed".equals(dateStatus)), page);

        return resultPage.map(e -> modelMapper.map(e, ProgramDataViewResponse.class));
    }

    public static Specification<ProgramEntity> findByCriteria(Integer idUser, Integer idCategory, Integer idDifficulty, Instant start, Boolean isBefore) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.isTrue(root.get("active"));

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

        saveProgramImages(savedProgram.getId(), programRequest.getProgramImages());
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

    public void delete(Integer id) throws ChangeSetPersister.NotFoundException {
        ProgramEntity programEntity = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        programEntity.setActive(false);

        repository.save(programEntity);
    }
}
