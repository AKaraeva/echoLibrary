package at.spengergasse.bookecho.presentation.api;

import at.spengergasse.bookecho.presentation.api.commands.CreateBookCommand;
import at.spengergasse.bookecho.service.BookService;
import at.spengergasse.bookecho.domain.Book;
import at.spengergasse.bookecho.presentation.api.dtos.BookDtos;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity <BookDtos.BasicInfoDto> createBook(@NotNull @Valid @RequestBody CreateBookCommand cmd) {
        var createdBook = bookService.createBook(cmd.title(), cmd.isbn());
        return createdBook
                .map(book->ResponseEntity.created(URI.create(createSelfLink(book)))
                        .body(BookDtos.BasicInfoDto.of(book)))
                .orElse(ResponseEntity.badRequest().build());
    }

    private String createSelfLink(Book book) {
        return "/api/books/" + book.getId().id();
    }
}
