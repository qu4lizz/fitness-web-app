package qu4lizz.ip.fitness.server.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedResponse {
    private Integer id;
    private String username;
}
