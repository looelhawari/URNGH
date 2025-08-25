package monitor.goodhealth.urngh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import monitor.goodhealth.urngh.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Method to find by email

    // Method to find user by email and password
    Optional<User> findByEmailAndPassword(String email, String password);
}
