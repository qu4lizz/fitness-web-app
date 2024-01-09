package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Instant;

@Data
public class MessageRequest {
    @NotNull
    private Integer chatId;
    @NotBlank
    private String message;
    @NotNull
    private Instant timestamp; 
    private Boolean isRead = false;
    @NotNull
    private Integer senderId;
}
