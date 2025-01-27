package at.spengergasse.bookecho.persistence;
import at.spengergasse.bookecho.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    //List<Reader> findByUsernameLikeIgnoreCase(String title);
}

