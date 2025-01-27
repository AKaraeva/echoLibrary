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
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Import(TestcontainersConfiguration.class)
class ReviewRepositoryTest {
    private @Autowired ReviewRepository reviewRepository;

    @Test
    void can_save(){
        //given
        var reader = Reader.builder()
                .username(Username.of("ainura"))
                .lastname(Lastname.of("Karaeva"))
                .firstname(Firstname.of("Ainura"))
                .email(new Email("kar@spg.at"))
                .password("secret")
                .build();

        var author = Author.builder()
                .lastname(Lastname.of("Orwell"))
                .firstname(Firstname.of("George"))
                .nationality(Country.USA)
                .bio("some text")
                .email(new Email("kar@spengergasse.at"))
                .build();

        var image = Image.builder()
                .description("book cover image")
                .path("http://example.com").build();

        var book = Book.builder()
                .title("1984")
                .isbn("9783161484100")
                .publicationDate(LocalDate.parse("1949-06-08"))
                .genre(Genre.FANTASY)
                .authors(List.of(author))
                .image(image)
                .build();

        var review  = Review.builder()
                .reviewer(reader)
                .book(book)
                .rating(5)
                .reviewText("awesome")
                .date(LocalDateTime.now())
                .build();

        //when
        var saved = reviewRepository.saveAndFlush(review);

        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getId().id()).isNotNull();
    }
}
