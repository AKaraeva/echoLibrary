package at.spengergasse.bookecho.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name ="authors")
public class Author extends AbstractPersistable<Long> {
    @Embedded
    private AuthorId authorId;
    private String lastName;
    private Country nationality;
    private String bio;

    @ManyToMany
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name ="author_id", foreignKey = @ForeignKey(name="fk_author_id")),
            inverseJoinColumns = @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name="fk_book_id")))
    private List<Book> writtenBooks;
    @Embeddable
    public record AuthorId(@GeneratedValue @NotNull Long id) { }
}
