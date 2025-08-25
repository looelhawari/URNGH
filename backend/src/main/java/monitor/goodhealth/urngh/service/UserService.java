package monitor.goodhealth.urngh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import monitor.goodhealth.urngh.model.User;
import monitor.goodhealth.urngh.repository.UserRepository;
import monitor.goodhealth.urngh.model.Patient;
import monitor.goodhealth.urngh.model.Doctor;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
    // Register a new user (either patient or doctor)
    public User registerUser(User user) {
        return userRepository.save(user); // Just save the user directly
    }

    // Find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));
    }

 // Authenticate user by email and password
    public User authenticateUser(String email, String password) {
        // Use findByEmailAndPassword from the repository
        return userRepository.findByEmailAndPassword(email, password)
                .orElse(null); // Return the user if found, otherwise null
    }
}
