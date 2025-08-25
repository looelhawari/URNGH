package monitor.goodhealth.urngh.repository;

import monitor.goodhealth.urngh.model.ChatHistory;
import monitor.goodhealth.urngh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

    // Retrieve chat history between two users
    List<ChatHistory> findBySenderAndReceiver(User sender, User receiver);

    // Retrieve all chat history for a user
    List<ChatHistory> findBySenderOrReceiver(User sender, User receiver);
    
    // Fetch all messages between two users
    List<ChatHistory> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

    // Fetch all messages for a specific user (sender or receiver)
    List<ChatHistory> findBySenderIdOrReceiverId(Long userId, Long userId2);
}
