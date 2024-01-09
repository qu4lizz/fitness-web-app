package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatRequest {
    @NotNull
    private Integer userOne;
    @NotNull
    private Integer userTwo;
}
