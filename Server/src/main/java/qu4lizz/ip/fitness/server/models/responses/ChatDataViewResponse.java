package qu4lizz.ip.fitness.server.models.responses;

import lombok.Data;
import qu4lizz.ip.fitness.server.models.dto.UserDTO;

@Data
public class ChatDataViewResponse {
    private Integer id;
    private UserDTO otherUser;
}
