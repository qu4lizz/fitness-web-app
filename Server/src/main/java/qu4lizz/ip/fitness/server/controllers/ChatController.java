package qu4lizz.ip.fitness.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qu4lizz.ip.fitness.server.models.responses.ChatDataViewResponse;
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
}
