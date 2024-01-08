package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserProfileEditRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String city;
    @NotBlank
    @Email
    private String mail;
    private MultipartFile image;
}
