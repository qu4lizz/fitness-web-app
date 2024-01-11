package qu4lizz.ip.fitness.server.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategorySubscribeRequest {
    private Integer id = null;
    @NotNull
    private Integer idCategory;
    @NotNull
    private Integer idUser;
    @NotNull
    private Boolean subscribe;
}
