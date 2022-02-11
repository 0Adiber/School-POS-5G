package at.kaindorf.examdb.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subject")
@CrossOrigin(origins = "*")
public class SubjectController {
}
