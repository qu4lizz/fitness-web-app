package qu4lizz.ip.fitness.server.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qu4lizz.ip.fitness.server.models.requests.LoginRequest;
import qu4lizz.ip.fitness.server.models.requests.MailConfirmationRequest;
import qu4lizz.ip.fitness.server.models.entities.UserEntity;
import qu4lizz.ip.fitness.server.models.requests.RegistrationRequest;
import qu4lizz.ip.fitness.server.models.responses.LoggedResponse;
import qu4lizz.ip.fitness.server.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public LoggedResponse login(@RequestBody LoginRequest request) throws BadRequestException {
        var res = service.login(request);
        if (res == null)
            throw new BadRequestException();
        return res;
    }

    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public LoggedResponse register(@ModelAttribute RegistrationRequest registrationRequest) {
        return service.register(registrationRequest);
    }

    @PostMapping("/mail-confirmation")
    public ResponseEntity<Object> confirmEmail(@RequestBody MailConfirmationRequest request) throws BadRequestException, ChangeSetPersister.NotFoundException {
        service.confirmEmail(request.getId());
        return ResponseEntity.ok().build();
    }
}
