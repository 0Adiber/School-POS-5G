package at.kaindorf.examdb.api;

import at.kaindorf.examdb.database.ClassnameRepository;
import at.kaindorf.examdb.database.StudentRepository;
import at.kaindorf.examdb.pojos.Classname;
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
@RequestMapping(value = "/classname")
@CrossOrigin(origins = "*")
public class ClassnameController {

    @Autowired
    ClassnameRepository classnameRepository;
    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<Iterable<Classname>> getAll() {
        return ResponseEntity.of(Optional.of(classnameRepository.findByOrderByClassname()));
    }

    @GetMapping("/{classid}")
    public ResponseEntity<Iterable<Student>> getStudentsByClassname(@PathVariable("classid") Long classid, @RequestParam(name = "page", defaultValue = "0") int pageNo) {
        Pageable page = PageRequest.of(pageNo, 10, Sort.by("lastname", "firstname"));
        return ResponseEntity.of(Optional.of(studentRepository.findByClassname_ClassIdEquals(classid, page)));
    }

}
