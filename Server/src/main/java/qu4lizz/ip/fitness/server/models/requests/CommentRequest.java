package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Instant;

@Data
public class CommentRequest {
    @NotNull
    private Integer idUser;
    @NotNull
    private Instant timestamp;
    @NotBlank
    private String comment;
    @NotNull
    private Integer idProgram;
}
