package at.kaindorf.examdb.database;

import at.kaindorf.examdb.pojos.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findByClassname_ClassIdEquals(Long classid, Pageable page);

}