package at.spengergasse.bookecho.persistence;
import at.spengergasse.bookecho.domain.Author;
import at.spengergasse.bookecho.domain.Book;
import at.spengergasse.bookecho.domain.Country;
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
class AuthorRepositoryTest {
    private @Autowired AuthorRepository authorRepository;
    @Test
    void can_save(){
        //given
        var author = Author.builder().build();

        //when
        var saved = authorRepository.saveAndFlush(author);
        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void will_find_author_with_correct_lastname_like(){
        //given

        Country country = Country.USA;
        var sirname = "Orwell";
        var author = Author.builder()
                .lastName(sirname)
                .nationality(country)
                .bio("some text")
                .build();

        var book = Book.builder()
                .title("1984")
                .authors(List.of(author))
                .isbn("978-3-16-148410-0")
                .publicationDate(1949)
                .genre(Genre.SCIFI)
                .build();
        
        authorRepository.saveAndFlush(author);

        //when
        var found = authorRepository.findByLastNameLikeIgnoreCase(sirname.toLowerCase());

        //then
        assertThat(found).hasSize(1).contains(author);
    }
}