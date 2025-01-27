package at.spengergasse.bookecho.persistence;

import at.spengergasse.bookecho.domain.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfRepository extends JpaRepository<Shelf, Shelf.ShelfId> {
}
