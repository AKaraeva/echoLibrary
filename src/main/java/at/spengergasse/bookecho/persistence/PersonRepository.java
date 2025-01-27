package at.spengergasse.bookecho.persistence;

import at.spengergasse.bookecho.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
