package qu4lizz.ip.fitness.server.models.responses;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qu4lizz.ip.fitness.server.models.dto.CategoryDTO;
import qu4lizz.ip.fitness.server.models.dto.DifficultyDTO;
import qu4lizz.ip.fitness.server.models.entities.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDataViewResponse {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Instant start;
    private Integer duration;
    private Integer idInstructor;
    private DifficultyDTO difficulty;
    private CategoryDTO category;
    private ProgramImageEntity programImages;
}
