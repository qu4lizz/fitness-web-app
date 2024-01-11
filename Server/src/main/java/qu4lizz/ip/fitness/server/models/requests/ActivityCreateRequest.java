package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Instant;

@Data
public class ActivityCreateRequest {
    private Integer id = null;
    @NotBlank
    private String exerciseType;
    @NotBlank
    private String duration;
    @NotBlank
    private String intensity;
    @NotBlank
    private String result;
    @NotNull
    private Instant timestamp;
    @NotNull
    private Integer idUser;
}
