package at.spengergasse.bookecho.domain;

import at.spengergasse.bookecho.persistence.converter.GenreConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter

@Entity
@Table(name ="books")
public class Book {
    @EmbeddedId
    private BookId id;

    @NotNull
    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "writtenBooks")
    private List <Author> authors;

    @NotNull
    @Column (name = "isbn")
    private String isbn;

    @Embedded
    private Image image;

    @Column(name = "publication_date")
    @PastOrPresent(message = "Publication date must be in the past or present.")
    private LocalDate publicationDate;

    @Column(name = "genre", columnDefinition = GenreConverter.COLUMN_DEFINITION)
    private Genre genre;

    @ManyToMany(mappedBy = "books")
    private List <Shelf> shelves;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    public Book(@NotNull String title) {
    }

    @Embeddable
    public record BookId(@GeneratedValue @NotNull Long id) {}
}
