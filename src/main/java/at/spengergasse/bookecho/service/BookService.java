package at.spengergasse.bookecho.service;

import at.spengergasse.bookecho.domain.Book;
import at.spengergasse.bookecho.persistence.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public Optional <Book> createBook(@NotNull String title, @NotNull String isbn) {
        return Optional.of(bookRepository.save(Book.builder().title(title).isbn(isbn).build()));
    }
}
