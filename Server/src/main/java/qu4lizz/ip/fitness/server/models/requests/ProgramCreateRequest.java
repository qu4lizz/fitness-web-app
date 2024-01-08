package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@ToString
public class ProgramCreateRequest {
    @NotBlank
    private String name;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer duration;
    @NotNull
    private Instant start;
    @NotBlank
    private String location;
    private String videoUrl;
    @NotNull
    private Boolean active;
    @NotNull
    private Integer idDifficulty;
    @NotNull
    private Integer idCategory;
    @NotNull
    private Integer idUser;
    @NotNull
    private MultipartFile[] programImages;
}
