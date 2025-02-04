package at.spengergasse.bookecho.persistence;

import at.spengergasse.bookecho.domain.Author;
import at.spengergasse.bookecho.domain.Lastname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByLastname(Lastname lastname);
}
