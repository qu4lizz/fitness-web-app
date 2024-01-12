package qu4lizz.ip.fitness.server.services;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import qu4lizz.ip.fitness.server.models.dto.UserDTO;
import qu4lizz.ip.fitness.server.models.entities.ChatEntity;
import qu4lizz.ip.fitness.server.models.entities.MessageEntity;
import qu4lizz.ip.fitness.server.models.requests.ChatRequest;
import qu4lizz.ip.fitness.server.models.requests.MessageRequest;
import qu4lizz.ip.fitness.server.models.responses.ChatDataViewResponse;
import qu4lizz.ip.fitness.server.models.responses.ChatResponse;
import qu4lizz.ip.fitness.server.repositories.ChatRepository;
import qu4lizz.ip.fitness.server.repositories.MessageRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;
    private final LogService logService;

    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository, ModelMapper modelMapper, LogService logService) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.logService = logService;
    }

    public List<ChatDataViewResponse> getUserChats(Integer userId) {
        List<ChatEntity> chats = chatRepository.findAllByUserOneOrUserTwoOrderByMessages(userId);

        logService.log("User with id " + userId + " fetching chats");

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

    public ChatResponse getSingleChat(Integer id) throws ChangeSetPersister.NotFoundException {
        ChatEntity chatEntity = chatRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        chatEntity.setMessages(chatEntity.getMessages().stream().sorted(Comparator.comparing(MessageEntity::getTimestamp)).toList());

        logService.log("Fetching chat with id " + id);

        return modelMapper.map(chatEntity, ChatResponse.class);
    }

    public void sendMessage(MessageRequest request) throws ChangeSetPersister.NotFoundException {
        chatRepository.findById(request.getChatId()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        MessageEntity entity = modelMapper.map(request, MessageEntity.class);

        logService.log("User with id " + request.getSenderId() + " sent new message");
        messageRepository.save(entity);
    }

    public Integer initChat(ChatRequest request) {
        ChatEntity entity = chatRepository.findByUserOneAndUserTwo(request.getIdUserOne(), request.getIdUserTwo());

        if (entity == null) {
            ChatEntity newEntity = modelMapper.map(request, ChatEntity.class);

            ChatEntity saved = chatRepository.save(newEntity);
            return saved.getId();
        }

        logService.log("User with id " + request.toString() + " started new conversation");

        return entity.getId();
    }
}
