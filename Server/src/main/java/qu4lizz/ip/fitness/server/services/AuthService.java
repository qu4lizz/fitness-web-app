package qu4lizz.ip.fitness.server.services;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import qu4lizz.ip.fitness.server.models.LoginRequest;
import qu4lizz.ip.fitness.server.models.entities.UserEntity;
import qu4lizz.ip.fitness.server.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository repository;
    private final ModelMapper modelMapper;

    public AuthService(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Integer login(LoginRequest request) {
        List<UserEntity> users = repository.findAll();

        for (var user : users) {
            if (user.getUsername().equals(request.getUsername()) && user.getPassword().equals(request.getPassword())) {
                if (!user.getVerified()) {
                    String message = "Please click on <a href='http://localhost:4200/mail-verification/" +
                            user.getId() +
                            "'>this link</a> to verify your email address.";
                    MailService.sendMail(user.getMail(), "Fitness Online Email Verification", message);
                }
                return user.getId();
            }
        }

        return null;
    }

    public Integer register(UserEntity entity) {
        var user = repository.save(entity);
        String message = "Please click on <a href='http://localhost:4200/mail-verification/" +
                user.getId() +
                "'>this link</a> to verify your email address.";
        MailService.sendMail(user.getMail(), "Fitness Online Email Verification", message);

        return user.getId();
    }

    public void confirmEmail(Integer id) throws BadRequestException {
        UserEntity user = modelMapper.map(repository.findById(id), UserEntity.class);
        if (user.getVerified())
            throw new BadRequestException("Already verified");
        else {
            user.setVerified(true);
            repository.saveAndFlush(user);
        }
    }
}
