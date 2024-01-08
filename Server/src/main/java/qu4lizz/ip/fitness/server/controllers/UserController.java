package qu4lizz.ip.fitness.server.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import qu4lizz.ip.fitness.server.models.requests.PasswordChangeRequest;
import qu4lizz.ip.fitness.server.models.requests.UserProfileEditRequest;
import qu4lizz.ip.fitness.server.models.responses.UserProfileEditResponse;
import qu4lizz.ip.fitness.server.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserProfileEditResponse getUserInfoForEditing(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        return userService.getUserInfoForEditing(id);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public void updateUser(@PathVariable Integer id, @ModelAttribute UserProfileEditRequest request) throws ChangeSetPersister.NotFoundException {
        userService.updateUser(id, request);
    }

    @PutMapping("/{id}/update-password")
    public void changePassword(@PathVariable Integer id, @RequestBody @Valid PasswordChangeRequest request) throws ChangeSetPersister.NotFoundException, BadRequestException {
        userService.changePassword(id, request);
    }
}
