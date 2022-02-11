package at.kaindorf.examdb.api;

import at.kaindorf.examdb.database.ExamRepository;
import at.kaindorf.examdb.pojos.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/exam")
@CrossOrigin(origins = "*")
public class ExamController {

    @Autowired
    ExamRepository examRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<Exam> deleteExam(@PathVariable("id") Long examId) {
        if(examRepository.existsById(examId)) {
            examRepository.deleteById(examId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
