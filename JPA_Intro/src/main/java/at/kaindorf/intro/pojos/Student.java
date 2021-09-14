package at.kaindorf.intro.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Data
//@NoArgsConstructor //für JPA braucht man parameterlosen constructor
@AllArgsConstructor
@Entity(name = "student") //"name" kann man weg lassen
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Column(nullable = false, length = 100)
    private String firstname;
    @Column(nullable = false, length = 150)
    private String lastname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    public Student() {
        studentId = UUID.randomUUID().toString();
    }

    public Student(String firstname, String lastname, LocalDate dateOfBirth) {
        this();
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }
}
