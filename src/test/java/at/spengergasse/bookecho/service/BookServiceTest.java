package at.spengergasse.bookecho.service;

import at.spengergasse.bookecho.domain.Book;
import at.spengergasse.bookecho.persistence.AuthorRepository;
import at.spengergasse.bookecho.persistence.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BookServiceTest {

    private @Mock BookRepository bookRepository;
    private @Mock AuthorRepository authorRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
       assumeThat(bookRepository).isNotNull();
       //assumeThat(authorRepository).isNotNull();
       bookService = new BookService(bookRepository);
    }
    @Test
    void will_return_optional_with_book (){
        //given
        var title = "1984";
        var isbn = "9783161484100";
        Book book = Book.builder().title(title).isbn(isbn).build();

        //when
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        //then
        Optional<Book> createdBook = bookService.createBook(title, isbn);
        assertThat(createdBook).isPresent()
                .hasValueSatisfying(
                        someBook -> {
                            assertThat(someBook.getTitle()).isEqualTo(title);
                            assertThat(someBook.getIsbn()).isEqualTo(isbn);
                        }
                );
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void will_return_service_exception_for_persistence_exception (){
        //to implement in the next iteration (Service/WebTests, 38 min)
    }
}