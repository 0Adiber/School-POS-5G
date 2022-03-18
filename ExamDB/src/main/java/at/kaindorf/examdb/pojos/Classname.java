package at.kaindorf.examdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classname")
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Classname {
    @Id
    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "classname", length = 10)
    private String classname;

    @OneToMany(mappedBy = "classname")
    @JsonIgnore
    private List<Student> students;

}