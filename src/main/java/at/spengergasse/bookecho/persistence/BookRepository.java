package at.spengergasse.bookecho.persistence;

import at.spengergasse.bookecho.domain.Author;
import at.spengergasse.bookecho.domain.Book;
import at.spengergasse.bookecho.domain.Lastname;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Book.BookId> {
    Optional<Book> findByTitleLikeIgnoreCase(String title);

    List<Book> findAllBooksByAuthorsLastname(Lastname lastname);
}
