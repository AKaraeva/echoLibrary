package at.spengergasse.bookecho.domain;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person extends AbstractPersistable<Long> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "firstname"))
    })
    private Firstname firstname;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "lastname"))
    })
    private Lastname lastname;

    @Column (name = "birthdate")
    private LocalDate birthdate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "username"))
    })
    private Username username;

    @Column (name = "account_created_at")
    private LocalDateTime accountCreatedAt;

    @Column (name = "email")
    private Email email;

    @Column (name = "password")
    private String password;

    //@Embeddable
    //public record PersonId(@GeneratedValue @NotNull Long id) {}

    public Person(Username username, Lastname lastname, Firstname firstname,
                  LocalDate birthdate, LocalDateTime accountCreatedAt,
                  Email email, String password) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.accountCreatedAt = accountCreatedAt;
        this.email = email;
        this.password = password;
    }
}
