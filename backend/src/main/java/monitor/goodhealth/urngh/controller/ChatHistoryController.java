package monitor.goodhealth.urngh.controller;

import monitor.goodhealth.urngh.model.ChatHistory;
import monitor.goodhealth.urngh.model.User;
import monitor.goodhealth.urngh.service.ChatHistoryService;
import monitor.goodhealth.urngh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/chat")
public class ChatHistoryController {
    @Autowired
    private ChatHistoryService chatHistoryService;
    @Autowired
    private UserService userService;

    // Endpoint to send a message
    @CrossOrigin
    @PostMapping("/send")
    public ChatHistory sendMessage(@RequestParam Long senderId,
                                   @RequestParam Long receiverId,
                                   @RequestParam String message) {
        User sender = userService.getUserById(senderId);
        User receiver = userService.getUserById(receiverId);
        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Invalid sender or receiver ID");
        }
        return chatHistoryService.saveMessage(message, sender, receiver);
    }

    // Endpoint to get chat history between two users
    @CrossOrigin
    @GetMapping("/history")
    public List<ChatHistory> getChatHistory(@RequestParam Long user1Id,
                                            @RequestParam Long user2Id) {
        User user1 = userService.getUserById(user1Id);
        User user2 = userService.getUserById(user2Id);
        if (user1 == null || user2 == null) {
            throw new IllegalArgumentException("Invalid user IDs");
        }
        return chatHistoryService.getChatHistory(user1, user2);
    }

    // Endpoint to get all chat history for a user
    @CrossOrigin
    @GetMapping("/user/{userId}")
    public List<ChatHistory> getUserChatHistory(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return chatHistoryService.getUserChatHistory(user);
    }
}
