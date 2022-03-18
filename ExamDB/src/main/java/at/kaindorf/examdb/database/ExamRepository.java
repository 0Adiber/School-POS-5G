package at.kaindorf.examdb.database;

import at.kaindorf.examdb.pojos.Exam;
import at.kaindorf.examdb.pojos.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByStudent_StudentIdEqualsOrderByExamId(Long studentId);

}