package at.kaindorf.examdb.database;

import at.kaindorf.examdb.pojos.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}