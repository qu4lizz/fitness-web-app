package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailConfirmationRequest {
    @NotNull

    private Integer id;
}
