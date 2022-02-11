package at.kaindorf.examdb.api;

import at.kaindorf.examdb.database.ExamRepository;
import at.kaindorf.examdb.pojos.Exam;
import at.kaindorf.examdb.pojos.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    ExamRepository examRepository;

    @GetMapping("/{studentId}")
    public ResponseEntity<Iterable<Exam>> getExamsByStudent(@PathVariable("studentId") Long studentId, @RequestParam(name = "page", defaultValue = "0") int pageNo) {
        Pageable page = PageRequest.of(pageNo, 10, Sort.by("lastname", "firstname"));
        return ResponseEntity.of(Optional.of(examRepository.findByStudent_StudentIdEquals(studentId, page)));
    }

}
