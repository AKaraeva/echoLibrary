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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Import(TestcontainersConfiguration.class)
class ShelfRepositoryTest {
    private @Autowired ShelfRepository shelfRepository;

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

        var book = Book.builder()
                .title("1984")
                .isbn("978-3-16-148410-0")
                .publicationDate(LocalDate.parse("1949-06-08"))
                .genre(Genre.FANTASY)
                .build();

        var shelf = Shelf.builder()
                .books(List.of(book))
                .reader(reader)
                .category(Category.WANT_TO_READ)
                .build();

        //when
        var saved = shelfRepository.saveAndFlush(shelf);

        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getId().id()).isNotNull();
    }
}
