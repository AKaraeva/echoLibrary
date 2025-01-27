package at.spengergasse.bookecho.persistence;

import at.spengergasse.bookecho.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    //List<Author> findByFirstnameLike(Lastname lastname);
}
