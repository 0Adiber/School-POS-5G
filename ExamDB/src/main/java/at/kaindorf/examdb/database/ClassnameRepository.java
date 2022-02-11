package at.kaindorf.examdb.database;

import at.kaindorf.examdb.pojos.Classname;
import at.kaindorf.examdb.pojos.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassnameRepository extends JpaRepository<Classname, Long> {

    List<Classname> findByOrderByClassname();

}