package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.Instant;

@Data
public class AdviceRequest {
    private Integer id;
    @NotBlank
    private String message;
    @NotNull
    private Instant timestamp;
    private Boolean isRead = false;
    @NotNull
    private Integer idUser;
}
