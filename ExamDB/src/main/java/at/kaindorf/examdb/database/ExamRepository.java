package at.kaindorf.examdb.database;

import at.kaindorf.examdb.pojos.Exam;
import at.kaindorf.examdb.pojos.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    Page<Exam> findByStudent_StudentIdEquals(Long studentId, Pageable page);

}