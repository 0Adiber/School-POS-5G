package at.kaindorf.examdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exam")
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id", nullable = false)
    private Long examId;

    @Column(name = "dateofexam")
    private LocalDate dateOfExam;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "student")
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject")
    @JsonIgnore
    private Subject subject;
}