package at.kaindorf.examdb.api;

import at.kaindorf.examdb.database.SubjectRepository;
import at.kaindorf.examdb.pojos.Classname;
import at.kaindorf.examdb.pojos.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
@CrossOrigin(origins = "*")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public ResponseEntity<Iterable<Subject>> getAll() {
        return ResponseEntity.of(Optional.of(subjectRepository.findByOrderByLongname()));
    }

}
