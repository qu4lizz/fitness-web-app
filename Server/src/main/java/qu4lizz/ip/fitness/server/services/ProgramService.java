package qu4lizz.ip.fitness.server.services;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qu4lizz.ip.fitness.server.models.entities.*;
import qu4lizz.ip.fitness.server.models.requests.ProgramCreateRequest;
import qu4lizz.ip.fitness.server.repositories.*;

import java.io.IOException;
import java.util.List;

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

    public List<ProgramEntity> findAll() {
        return repository.findAll();
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
