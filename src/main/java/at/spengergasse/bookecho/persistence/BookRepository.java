package at.spengergasse.bookecho.persistence;

import at.spengergasse.bookecho.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Book.BookId> {
    List<Book> findByTitleLikeIgnoreCase(String title);
}
