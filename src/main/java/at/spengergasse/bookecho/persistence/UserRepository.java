package at.spengergasse.bookecho.persistence;

import at.spengergasse.bookecho.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByEmail(String email);
}
