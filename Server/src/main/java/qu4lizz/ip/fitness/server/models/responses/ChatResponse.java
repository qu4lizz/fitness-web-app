package qu4lizz.ip.fitness.server.models.responses;

import lombok.Data;
import qu4lizz.ip.fitness.server.models.dto.UserDTO;
import qu4lizz.ip.fitness.server.models.entities.MessageEntity;

import java.util.List;

@Data
public class ChatResponse {
    private Integer id;
    private UserDTO userOne;
    private UserDTO userTwo;
    private List<MessageEntity> messages;
}
