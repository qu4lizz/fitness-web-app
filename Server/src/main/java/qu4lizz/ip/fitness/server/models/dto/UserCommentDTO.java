package qu4lizz.ip.fitness.server.models.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserCommentDTO {
    private UserDTO user;
    private Integer id;
    private String comment;
    private Instant timestamp;
}
