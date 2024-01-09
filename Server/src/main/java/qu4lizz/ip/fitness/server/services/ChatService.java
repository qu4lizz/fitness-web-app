package qu4lizz.ip.fitness.server.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import qu4lizz.ip.fitness.server.models.dto.UserDTO;
import qu4lizz.ip.fitness.server.models.entities.ChatEntity;
import qu4lizz.ip.fitness.server.models.responses.ChatDataViewResponse;
import qu4lizz.ip.fitness.server.repositories.ChatRepository;
import qu4lizz.ip.fitness.server.repositories.MessageRepository;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository, ModelMapper modelMapper) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    public List<ChatDataViewResponse> getUserChats(Integer userId) {
        List<ChatEntity> chats = chatRepository.findAllByUserOneOrUserTwoOrderByMessages(userId);

        return chats.stream()
                .map(chatEntity -> {
                    ChatDataViewResponse chatDataViewResponse = modelMapper.map(chatEntity, ChatDataViewResponse.class);

                    if (chatEntity.getUserOne().getId().equals(userId)) {
                        chatDataViewResponse.setOtherUser(modelMapper.map(chatEntity.getUserTwo(), UserDTO.class));
                    } else {
                        chatDataViewResponse.setOtherUser(modelMapper.map(chatEntity.getUserOne(), UserDTO.class));
                    }

                    return chatDataViewResponse;
                })
                .toList();
    }
}
