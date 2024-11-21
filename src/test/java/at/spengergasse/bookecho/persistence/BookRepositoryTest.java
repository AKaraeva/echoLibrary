package at.spengergasse.bookecho.persistence;
import at.spengergasse.bookecho.domain.Author;
import at.spengergasse.bookecho.domain.Book;
import at.spengergasse.bookecho.domain.Image;
import at.spengergasse.bookecho.domain.Genre;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BookRepositoryTest {
    private @Autowired BookRepository bookRepository;
@Test
    void can_save(){
        //given
        var book = Book.builder().build();

        //when
        var saved = bookRepository.saveAndFlush(book);
        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void will_find_book_with_correct_title_like(){
        //given
        String bookTitle = "1984";
        var author = Author.builder().bio("some text").build();
        //var image = Image.builder()
                //.description("book cover image")
                //.path("http://example.com").build();
        var book = Book.builder()
                .title(bookTitle)
                .authors(List.of(author))
                .isbn("978-3-16-148410-0")
                .publicationDate(1949)
                .genre(Genre.SCIFI).build();

        bookRepository.saveAndFlush(book);

        //when
        var found = bookRepository.findByTitleLikeIgnoreCase(bookTitle.toLowerCase());

        //then
        assertThat(found).hasSize(1).contains(book);
    }
}