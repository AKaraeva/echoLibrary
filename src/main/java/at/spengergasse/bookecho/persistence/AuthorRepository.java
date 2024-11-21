package at.spengergasse.bookecho.persistence;

import at.spengergasse.bookecho.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Author.AuthorId> {
    List<Author> findByLastNameLikeIgnoreCase(String lastName);
}
