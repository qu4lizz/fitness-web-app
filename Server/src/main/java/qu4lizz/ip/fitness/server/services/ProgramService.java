package qu4lizz.ip.fitness.server.services;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qu4lizz.ip.fitness.server.config.converters.ProgramImageListConverter;
import qu4lizz.ip.fitness.server.models.entities.*;
import qu4lizz.ip.fitness.server.models.requests.ProgramCreateRequest;
import qu4lizz.ip.fitness.server.models.responses.ProgramDataViewResponse;
import qu4lizz.ip.fitness.server.repositories.*;

import java.io.IOException;

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
        Page<ProgramEntity> resultPage;

        if (idCategory != null && idDifficulty != null) {
            resultPage = repository.findAllByDifficultyIdAndCategoryId(page, Integer.parseInt(idDifficulty), Integer.parseInt(idCategory));
        } else if (idCategory != null) {
            resultPage = repository.findAllByCategoryId(page, Integer.parseInt(idCategory));
        } else if (idDifficulty != null) {
            resultPage = repository.findAllByDifficultyId(page, Integer.parseInt(idDifficulty));
        } else {
            resultPage = repository.findAll(page);
        }

        return resultPage.map(e -> modelMapper.map(e, ProgramDataViewResponse.class));
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
}
