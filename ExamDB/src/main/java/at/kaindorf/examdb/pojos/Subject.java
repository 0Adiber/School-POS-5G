package at.kaindorf.examdb.pojos;

import at.kaindorf.examdb.pojos.Exam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @Column(name = "longname", length = 100)
    private String longname;

    @Column(name = "shortname", length = 10)
    private String shortname;

    @Column(name = "written", nullable = false)
    private Boolean written = false;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<Exam> exams;

}