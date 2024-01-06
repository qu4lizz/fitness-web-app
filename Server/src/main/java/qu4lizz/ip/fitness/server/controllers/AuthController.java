package qu4lizz.ip.fitness.server.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qu4lizz.ip.fitness.server.models.requests.LoginRequest;
import qu4lizz.ip.fitness.server.models.requests.MailConfirmationRequest;
import qu4lizz.ip.fitness.server.models.entities.UserEntity;
import qu4lizz.ip.fitness.server.models.requests.RegistrationRequest;
import qu4lizz.ip.fitness.server.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Integer login(@RequestBody LoginRequest request) throws BadRequestException {
        Integer res = service.login(request);
        if (res == null)
            throw new BadRequestException();
        return res;
    }

    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public Integer register(@ModelAttribute RegistrationRequest registrationRequest) {
        return service.register(new UserEntity(
                registrationRequest.getName(),
                registrationRequest.getSurname(),
                registrationRequest.getUsername(),
                registrationRequest.getPassword(),
                registrationRequest.getMail(),
                registrationRequest.getCity(),
                registrationRequest.getImage()
        ));
    }

    @PostMapping("/mail-confirmation")
    public ResponseEntity<Object> confirmEmail(@RequestBody MailConfirmationRequest request) throws BadRequestException {
        service.confirmEmail(request.getId());
        return ResponseEntity.ok().build();
    }
}