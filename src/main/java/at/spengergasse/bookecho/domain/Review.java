package at.spengergasse.bookecho.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name ="reviews_to_books")
public class Review {
    @EmbeddedId
    private ReviewId id;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name ="reader_id", foreignKey = @ForeignKey(name="fk_reader_id"))
    private Reader reviewer;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name="fk_book_id"))
    private Book book;

    @NotNull
    private int rating;

    private String reviewText;

    private LocalDateTime date;

    @Embeddable
    public record ReviewId(@GeneratedValue @NotNull Long id) {}
}

