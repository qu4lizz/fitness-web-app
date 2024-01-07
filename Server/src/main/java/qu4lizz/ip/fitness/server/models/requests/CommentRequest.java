package qu4lizz.ip.fitness.server.models.requests;

import lombok.Data;

import java.time.Instant;

@Data
public class CommentRequest {
    private Integer idUser;
    private Instant timestamp;
    private String comment;
    private Integer idProgram;
}
