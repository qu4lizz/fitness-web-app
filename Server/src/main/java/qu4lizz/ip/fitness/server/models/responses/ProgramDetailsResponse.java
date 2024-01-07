package qu4lizz.ip.fitness.server.models.responses;

import lombok.Data;
import qu4lizz.ip.fitness.server.models.dto.CategoryDTO;
import qu4lizz.ip.fitness.server.models.dto.DifficultyDTO;
import qu4lizz.ip.fitness.server.models.dto.UserCommentDTO;
import qu4lizz.ip.fitness.server.models.dto.UserDTO;
import qu4lizz.ip.fitness.server.models.entities.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class ProgramDetailsResponse {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private Instant start;
    private String location;
    private String videoUrl;
    private Boolean active; // TODO: think
    private DifficultyDTO difficulty;
    private CategoryDTO category;
    private UserDTO instructor;
    private List<ProgramImageEntity> programImages;
    private List<UserCommentDTO> comments;
}
