package at.spengergasse.bookecho.domain;

import at.spengergasse.bookecho.persistence.converter.CountryConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name ="authors")
public class Author extends User {
    @NotNull
    @Column(columnDefinition = CountryConverter.COLUMN_DEFINITION)
    private Country nationality;

    private String bio;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(foreignKey = @ForeignKey(name="fk_author_id")),
            inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name="fk_bookId")))
    private List<Book> writtenBooks;

    @Builder
    public Author(Username username,
                  Lastname lastname, Firstname firstname,
                  LocalDate birthdate, LocalDateTime accountCreatedAt,
                  Email email, String password, Country nationality,
                  String bio, List<Book> writtenBooks) {
        super(username, lastname, firstname, birthdate, accountCreatedAt, email, password);
        this.nationality = nationality;
        this.bio = bio;
        this.writtenBooks = writtenBooks;
    }
}
