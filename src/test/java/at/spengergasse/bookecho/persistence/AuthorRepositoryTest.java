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
class AuthorRepositoryTest {

    private @Autowired AuthorRepository authorRepository;

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

        var author = Author.builder()
                .lastname(Lastname.of("Orwell"))
                .firstname(Firstname.of("George"))
                .nationality(Country.USA)
                .bio("some text")
                .email(new Email("kar@spengergasse.at"))
                .writtenBooks(List.of(book))
                .build();

        //when
        var saved = authorRepository.saveAndFlush(author);

        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

   /* @Test
    void will_find_author_with_correct_lastname_like(){
        //given
        var author = Author.builder()
                .lastname(Lastname.of("Orwell"))
                .firstname(Firstname.of("George"))
                .nationality(Country.USA)
                .bio("some text")
                .email(new Email("kar@spengergasse.at"))
                .build();

        var book = Book.builder()
                .title("1984")
                .authors(List.of(author))
                .isbn("978-3-16-148410-0")
                .publicationDate(LocalDate.parse("1949-06-08"))
                .genre(Genre.SCIFI)
                .build();
        
        authorRepository.saveAndFlush(author);

        //when
        var found = authorRepository.findByFirstnameLike(Lastname.of(String.valueOf("George")));

        //then
        assertThat(found).hasSize(1).contains(author);
    }*/
}