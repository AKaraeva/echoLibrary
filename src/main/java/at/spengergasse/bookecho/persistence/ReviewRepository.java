package at.spengergasse.bookecho.persistence;

import at.spengergasse.bookecho.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
