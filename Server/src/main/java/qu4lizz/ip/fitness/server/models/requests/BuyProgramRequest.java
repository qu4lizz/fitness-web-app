package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BuyProgramRequest {
    @NotNull
    private Integer idUser;
    @NotNull
    private Integer idProgram;
    @NotBlank
    private String paymentType;
}
