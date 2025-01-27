package at.spengergasse.bookecho.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name ="readers")
public class Reader extends Person {

    @OneToMany(mappedBy = "reader")
    private List<Shelf> shelves;

    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviews;

    @Builder
    public Reader(Firstname firstname, Lastname lastname,
                  LocalDate birthdate, Username username, LocalDateTime accountCreatedAt,
                  Email email, String password, List<Review> reviews,
                  List<Shelf> shelves) {
        super(firstname, lastname, birthdate, username, accountCreatedAt, email, password);
        this.reviews = reviews;
        this.shelves = shelves;
    }
}
