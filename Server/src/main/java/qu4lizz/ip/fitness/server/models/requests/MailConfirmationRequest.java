package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailConfirmationRequest {
    @NotNull
    private Integer id;
}
