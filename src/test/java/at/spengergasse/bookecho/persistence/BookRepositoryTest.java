package at.spengergasse.bookecho.persistence;
import at.spengergasse.bookecho.FixtureFactory;
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
    void can_save() {
        //given
        var book = FixtureFactory.book();
        //when
        var saved = bookRepository.saveAndFlush(book);
        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getId().id()).isNotNull();
    }

    //TODO fix this test
   /* @Test
    void will_find_book_with_correct_title_like() {
        // given
        var book = FixtureFactory.book();
        bookRepository.saveAndFlush(book);

        // when
        var found = bookRepository.findByTitleLikeIgnoreCase("Determined");

        // then
        assertThat(found).isPresent()
                .hasValueSatisfying(b -> assertThat(b.getTitle()).isEqualTo(book.getTitle()));
    }*/
}