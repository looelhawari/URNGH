package monitor.goodhealth.urngh.service;

import monitor.goodhealth.urngh.model.ChatHistory;
import monitor.goodhealth.urngh.model.User;
import monitor.goodhealth.urngh.repository.ChatHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatHistoryService {

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    // Save a new message
    public ChatHistory saveMessage(String message, User sender, User receiver) {
        ChatHistory chatHistory = new ChatHistory(message, LocalDateTime.now(), sender, receiver);
        return chatHistoryRepository.save(chatHistory);
    }

    // Retrieve chat history between two users
    public List<ChatHistory> getChatHistory(User sender, User receiver) {
        return chatHistoryRepository.findBySenderAndReceiver(sender, receiver);
    }

    // Retrieve all chat history for a specific user
    public List<ChatHistory> getUserChatHistory(User user) {
        return chatHistoryRepository.findBySenderOrReceiver(user, user);
    }
}
