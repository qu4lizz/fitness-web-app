package qu4lizz.ip.fitness.server.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qu4lizz.ip.fitness.server.models.LoginRequest;
import qu4lizz.ip.fitness.server.models.MailConfirmationRequest;
import qu4lizz.ip.fitness.server.models.RssResponse;
import qu4lizz.ip.fitness.server.models.entities.UserEntity;
import qu4lizz.ip.fitness.server.services.AuthService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
    public Integer register(@RequestParam("image") MultipartFile image,
                            @RequestParam("name") String name,
                            @RequestParam("surname") String surname,
                            @RequestParam("password") String password,
                            @RequestParam("city") String city,
                            @RequestParam("mail") String mail,
                            @RequestParam("username") String username) {

        return service.register(new UserEntity(name, surname, username, password, mail, city, image));
    }

    @PostMapping("/mail-confirmation")
    public ResponseEntity<Object> confirmEmail(@RequestBody MailConfirmationRequest request) throws BadRequestException {
        service.confirmEmail(request.getId());
        return ResponseEntity.ok().build();
    }
}
