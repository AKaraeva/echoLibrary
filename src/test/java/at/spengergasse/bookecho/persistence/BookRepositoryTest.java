package at.spengergasse.bookecho.persistence;
import at.spengergasse.bookecho.TestcontainersConfiguration;
import at.spengergasse.bookecho.domain.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Import(TestcontainersConfiguration.class)
class BookRepositoryTest {
    private @Autowired BookRepository bookRepository;
@Test
    void can_save(){
        //given
        var book = Book.builder()
                    .genre(Genre.FANTASY)
                    .image(Image.builder().description("cover image").path("http://example.com").build())
                    .isbn("9783161484100")
                    .publicationDate(LocalDate.parse("1949-06-08"))
                    .title("1984")
                    .build();
        //when
        var saved = bookRepository.saveAndFlush(book);
        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getId().id()).isNotNull();
    }

    @Test
    void will_find_book_with_correct_title_like(){
        //given
        var author = Author.builder()
                .lastname(Lastname.of("Orwell"))
                .firstname(Firstname.of("George"))
                .nationality(Country.USA)
                .bio("some text")
                .email(new Email("kar@spengergasse.at"))
                .build();

        var image = Image.builder()
                .path("http://example.com").description("cover image").build();

        var book = Book.builder()
                .title("1984")
                .isbn("9783161484100")
                .publicationDate(LocalDate.parse("1949-06-08"))
                .genre(Genre.FANTASY)
                .authors(List.of(author))
                .image(image)
                .build();

        //when
        bookRepository.saveAndFlush(book);

        var found = bookRepository.findByTitleLikeIgnoreCase("1984");

        //then
        assertThat(found).hasSize(1).contains(book);
    }
}