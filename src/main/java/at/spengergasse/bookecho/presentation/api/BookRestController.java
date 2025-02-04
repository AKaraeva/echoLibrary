package at.spengergasse.bookecho.presentation.api;

import at.spengergasse.bookecho.commands.CreateBookCommand;
import at.spengergasse.bookecho.commands.ReplaceBookCommand;
import at.spengergasse.bookecho.domain.Lastname;
import at.spengergasse.bookecho.service.BookService;
import at.spengergasse.bookecho.domain.Book;
import at.spengergasse.bookecho.dtos.BookDtos;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static at.spengergasse.bookecho.dtos.BookDtos.*;

@RequiredArgsConstructor

@RestController
@RequestMapping (BookRestController.BASE_URL)
public class BookRestController {

    protected static final String BASE_URL = ApiSupport.API + "/books";
    protected static final String PATH_VAR_ID = "/{bookId}";
    protected static final String BOOK_PATH = "/{bookId}";
    protected static final String BOOK_ROUTE = BASE_URL + PATH_VAR_ID;

    private final BookService bookService;
// /api/books?author=<authorId>
    @GetMapping
    public ResponseEntity<List<BookDtos.BasicInfoDto>>getAllBooks(@RequestParam Optional<Lastname> lastname) {

        List<BookDtos.BasicInfoDto> books =
               lastname.map(bookService::getAllBooksByAuthor)
                        .orElseGet(bookService::getAllBooks)
                        .stream()
                        .map(BookDtos.BasicInfoDto::of)
                        .toList();

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{title}")
    public ResponseEntity<BookDtos.BasicInfoDto>getBook(String title) {
                return bookService.findBookByTile(title)
                        .map(BookDtos.BasicInfoDto::of)
                        .map(ResponseEntity::ok)
                        .orElseGet(() ->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity <BasicInfoDto> createBook(@NotNull @Valid @RequestBody CreateBookCommand cmd) {
        var book = bookService.createBook(cmd.title(), cmd.isbn());
        var locationHeader = createSelfLink(book);
        return ResponseEntity.created(URI.create(locationHeader)).body(BasicInfoDto.of(book));
    }

    //idempotent
    @PutMapping(BOOK_PATH)
    public ResponseEntity<BookDtos.BasicInfoDto>replaceBook(@PathVariable Book.BookId bookId, @RequestBody ReplaceBookCommand cmd) {
        return ResponseEntity.ok(BookDtos.BasicInfoDto.of(bookService.replaceBook(bookId, cmd)));
    }

    //idempotent
    @DeleteMapping(BOOK_PATH)
    public ResponseEntity<Void> deleteBook(@PathVariable Book.BookId bookId) {

        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    private String createSelfLink(Book book) {
        return BASE_URL + book.getId().id();
    }
}
