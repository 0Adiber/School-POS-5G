package at.kaindorf.examdb.database;

import at.kaindorf.examdb.pojos.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByOrderByLongname();

}