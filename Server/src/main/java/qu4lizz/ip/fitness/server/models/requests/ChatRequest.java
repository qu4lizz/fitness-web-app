package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatRequest {
    private Integer id = null;
    @NotNull
    private Integer idUserOne;
    @NotNull
    private Integer idUserTwo;
}
