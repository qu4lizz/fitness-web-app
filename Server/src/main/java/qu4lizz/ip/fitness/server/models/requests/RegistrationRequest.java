package qu4lizz.ip.fitness.server.models.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegistrationRequest {
    private MultipartFile image;
    private String name;
    private String surname;
    private String password;
    private String city;
    private String mail;
    private String username;
}
