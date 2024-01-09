package qu4lizz.ip.fitness.server.controllers;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import qu4lizz.ip.fitness.server.models.requests.ChatRequest;
import qu4lizz.ip.fitness.server.models.requests.MessageRequest;
import qu4lizz.ip.fitness.server.models.responses.ChatDataViewResponse;
import qu4lizz.ip.fitness.server.models.responses.ChatResponse;
import qu4lizz.ip.fitness.server.services.ChatService;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/user/{id}")
    public List<ChatDataViewResponse> getUserChats(@PathVariable Integer id) {
        return chatService.getUserChats(id);
    }

    @GetMapping("/{id}")
    public ChatResponse getSingleChat(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        return chatService.getSingleChat(id);
    }

    @PostMapping("/message")
    public void sendMessage(@RequestBody MessageRequest request) throws ChangeSetPersister.NotFoundException {
        chatService.sendMessage(request);
    }

    @PostMapping
    public Integer initChat(@RequestBody ChatRequest request) {
        return chatService.initChat(request);
    }
}
