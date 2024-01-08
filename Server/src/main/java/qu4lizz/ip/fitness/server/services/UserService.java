package qu4lizz.ip.fitness.server.services;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import qu4lizz.ip.fitness.server.models.entities.AdviceEntity;
import qu4lizz.ip.fitness.server.models.entities.UserEntity;
import qu4lizz.ip.fitness.server.models.requests.AdviceRequest;
import qu4lizz.ip.fitness.server.models.requests.PasswordChangeRequest;
import qu4lizz.ip.fitness.server.models.requests.UserProfileEditRequest;
import qu4lizz.ip.fitness.server.models.responses.UserProfileEditResponse;
import qu4lizz.ip.fitness.server.repositories.AdviceRepository;
import qu4lizz.ip.fitness.server.repositories.UserRepository;

import java.io.IOException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AdviceRepository adviceRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, AdviceRepository adviceRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.adviceRepository = adviceRepository;
        this.modelMapper = modelMapper;
    }

    public UserProfileEditResponse getUserInfoForEditing(Integer id) throws ChangeSetPersister.NotFoundException {
        UserEntity entity = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return modelMapper.map(entity, UserProfileEditResponse.class);
    }

    public void updateUser(Integer id, UserProfileEditRequest request) throws ChangeSetPersister.NotFoundException {
        UserEntity entity = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (!entity.getMail().equals(request.getMail()))
            entity.setVerified(false);

        entity.setCity(request.getCity());
        entity.setMail(request.getMail());
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        try {
            if (request.getImage() != null)
                entity.setImage(request.getImage().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userRepository.saveAndFlush(entity);
    }

    public void changePassword(Integer id, PasswordChangeRequest request) throws ChangeSetPersister.NotFoundException, BadRequestException {
        UserEntity entity = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (entity.getPassword().equals(request.getOldPassword())) {
            entity.setPassword(request.getNewPassword());

            userRepository.saveAndFlush(entity);
        }
        else {
            throw new BadRequestException("Incorrect old password");
        }
    }


    public void askForAdvice(AdviceRequest request) {
        AdviceEntity entity = modelMapper.map(request, AdviceEntity.class);
        System.out.println(""+entity.getIsRead() + entity.getId() + " " + entity.getUser().getId());
        adviceRepository.save(entity);
        System.out.println("succ");
    }
}
