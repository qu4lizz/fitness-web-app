package qu4lizz.ip.fitness.server.services;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import qu4lizz.ip.fitness.server.models.requests.LoginRequest;
import qu4lizz.ip.fitness.server.models.entities.UserEntity;
import qu4lizz.ip.fitness.server.models.requests.RegistrationRequest;
import qu4lizz.ip.fitness.server.models.responses.LoggedResponse;
import qu4lizz.ip.fitness.server.repositories.UserRepository;

import java.util.List;

@Service
public class AuthService {
    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final LogService logService;

    public AuthService(UserRepository repository, ModelMapper modelMapper, LogService logService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.logService = logService;
    }

    public LoggedResponse login(LoginRequest request) {
        List<UserEntity> users = repository.findAll();

        for (var user : users) {
            if (user.getUsername().equals(request.getUsername()) && user.getPassword().equals(request.getPassword())) {
                if (!user.getVerified()) {
                    String message = "Please click on <a href='http://localhost:4200/mail-verification/" +
                            user.getId() +
                            "'>this link</a> to verify your email address.";
                    MailService.sendMail(user.getMail(), "Fitness Online Email Verification", message);
                }
                logService.log("User with username '" + request.getUsername() + "' logged in");
                return modelMapper.map(user, LoggedResponse.class);
            }
        }
        logService.log("User with username '" + request.getUsername() + "' failed login");

        return null;
    }

    public LoggedResponse register(RegistrationRequest registrationRequest) {
        UserEntity entity = new UserEntity(
                registrationRequest.getName(),
                registrationRequest.getSurname(),
                registrationRequest.getUsername(),
                registrationRequest.getPassword(),
                registrationRequest.getMail(),
                registrationRequest.getCity(),
                registrationRequest.getImage()
        );

        var user = repository.save(entity);

        logService.log("User with username '" + registrationRequest.getUsername() + "' just registered");

        String message = "Please click on <a href='http://localhost:4200/mail-verification/" +
                user.getId() +
                "'>this link</a> to verify your email address.";
        MailService.sendMail(user.getMail(), "Fitness Online Email Verification", message);

        return new LoggedResponse(user.getId(), user.getUsername());
    }

    public void confirmEmail(Integer id) throws BadRequestException, ChangeSetPersister.NotFoundException {
        UserEntity user = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (user.getVerified())
            throw new BadRequestException("Already verified");
        else {
            user.setVerified(true);
            repository.saveAndFlush(user);
            logService.log("User with id " + id + " verified email");
        }
    }
}
