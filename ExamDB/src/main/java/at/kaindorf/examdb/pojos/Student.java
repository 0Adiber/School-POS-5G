package at.kaindorf.examdb.pojos;

import at.kaindorf.examdb.pojos.Classname;
import at.kaindorf.examdb.pojos.Exam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "firstname", length = 80)
    private String firstname;

    @Column(name = "lastname", length = 80)
    private String lastname;

    @ManyToOne(optional = false)
    @JoinColumn(name = "classname", nullable = false)
    @JsonIgnore
    private Classname classname;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Exam> exams;

}