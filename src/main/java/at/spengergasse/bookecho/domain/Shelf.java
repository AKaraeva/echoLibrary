package at.spengergasse.bookecho.domain;

import at.spengergasse.bookecho.persistence.converter.CategoryConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name ="shelves")
public class Shelf {

    @EmbeddedId
    private ShelfId id;

    @NotNull
    @Column(columnDefinition = CategoryConverter.COLUMN_DEFINITION)
    private Category category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "shelf_books",
            joinColumns = @JoinColumn(foreignKey = @ForeignKey(name="fk_shelf_id")),
            inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name="fk_shelf_books_book_id"))
    )
    private List<Book> books;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "reader_id", foreignKey = @ForeignKey(name="fk_readerId"))
    private Reader reader;

    @Embeddable
    public record ShelfId(@GeneratedValue @NotNull Long id) {}
}
