package at.spengergasse.bookecho.domain;

import at.spengergasse.bookecho.persistence.converter.GenreConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name ="books")
public class Book extends AbstractPersistable<Long> {
    @Id @GeneratedValue
    Long id;
    private String title;

    @ManyToMany(mappedBy = "writtenBooks")
    private List <Author> authors;

    private String isbn;

    //@JoinTable (name = "images", joinColumns = @JoinColumn(foreignKey = @ForeignKey(name="fk_image")))
    //private Image image;

    private int publicationDate;

    @Column(columnDefinition = GenreConverter.COLUMN_DEFINITION)
    private Genre genre;
}
