package at.spengergasse.bookecho.service;

import at.spengergasse.bookecho.domain.Book;
import at.spengergasse.bookecho.domain.Lastname;
import at.spengergasse.bookecho.persistence.BookRepository;
import at.spengergasse.bookecho.commands.ReplaceBookCommand;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(@NotNull String title, @NotNull String isbn) {
        try {
            return bookRepository.save(Book.builder().title(title).isbn(isbn).build());
        } catch (PersistenceException pEx) {
            throw ServiceException.whileSavingBook(title, isbn, pEx);
        }
    }

    public void deleteBook(Book.BookId bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookByTile(String title) {
        return bookRepository.findByTitleLikeIgnoreCase(title);
    }

    public List<Book> getAllBooksByAuthor(@NotNull Lastname lastname) {
        return bookRepository.findAllBooksByAuthorsLastname(lastname);
    }


    public Book replaceBook(Book.BookId bookId, ReplaceBookCommand cmd) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    if (cmd.title() != null) book.setTitle(cmd.title());
                    if (cmd.isbn() != null) book.setIsbn(cmd.isbn());

                    if (cmd.bookId() != null) bookRepository.findById(cmd.bookId());

                    return book;
                }).orElseThrow(() -> new EntityNotFoundException("Book with id " + bookId + " not found."));
    }
}

