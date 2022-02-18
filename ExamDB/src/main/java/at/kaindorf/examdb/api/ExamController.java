package at.kaindorf.examdb.api;

import at.kaindorf.examdb.database.ExamRepository;
import at.kaindorf.examdb.database.StudentRepository;
import at.kaindorf.examdb.database.SubjectRepository;
import at.kaindorf.examdb.pojos.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Random;

@RestController
@RequestMapping(value = "/exam")
@CrossOrigin(origins = "*")
public class ExamController {

    @Autowired
    ExamRepository examRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;

    private static final Random RAND = new Random();

    @PostMapping
    public ResponseEntity<Exam> createCustomer(@RequestBody Exam exam, @RequestParam(value = "studentId") Long studentId, @RequestParam(value = "subjectId") Long subjectId) {
        try {
            exam.setExamId(RAND.nextInt(90_000)+10_000L);
            exam.setStudent(studentRepository.findById(studentId).get());
            exam.setSubject(subjectRepository.findById(subjectId).get());
            examRepository.save(exam);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exam> deleteExam(@PathVariable("id") Long examId) {
        if(examRepository.existsById(examId)) {
            examRepository.deleteById(examId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Exam> patchExam(@PathVariable("id") Long id, @RequestBody() Exam patch) {
        if(examRepository.existsById(id)) {
            Exam customer = examRepository.findById(id).get();

            for(Field field : Exam.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = ReflectionUtils.getField(field, patch);
                if(value != null) {
                    ReflectionUtils.setField(field, customer, value);
                }
            }
            examRepository.save(customer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
