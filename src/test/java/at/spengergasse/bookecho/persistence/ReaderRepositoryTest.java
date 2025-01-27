package at.spengergasse.bookecho.persistence;
import at.spengergasse.bookecho.TestcontainersConfiguration;
import at.spengergasse.bookecho.domain.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Import(TestcontainersConfiguration.class)
class ReaderRepositoryTest {
    private @Autowired ReaderRepository readerRepository;

    @Test
    void can_save() {
        //given
        var reader = Reader.builder()
                .username(Username.of("ainura"))
                .lastname(Lastname.of("Karaeva"))
                .firstname(Firstname.of("Ainura"))
                .email(new Email("kar@spg.at"))
                .password("secret")
                .build();
        //when
        var saved = readerRepository.saveAndFlush(reader);
        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

   /* @Test
    void will_find_reader_with_correct_username_like() {
        //given


        var reader = Reader.builder()
                .username("username")
                .build();

        readerRepository.saveAndFlush(reader);

        //when
        var found = readerRepository.findByUsernameLikeIgnoreCase(username.toLowerCase());

        //then
        assertThat(found).hasSize(1).contains(reader);
    }*/
}
