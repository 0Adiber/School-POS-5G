package at.kaindorf.schooldb.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(name = "ClassTeacher.findByName", query = "SELECT t FROM ClassTeacher t WHERE UPPER(t.lastname) LIKE UPPER(:name)"),
        @NamedQuery(name = "ClassTeacher.findAll", query = "SELECT t FROM ClassTeacher t"),
        @NamedQuery(name = "ClassTeacher.findByGrade", query = "SELECT t FROM ClassTeacher t WHERE t.classname.grade = :grade"),
        @NamedQuery(name = "ClassTeacher.countAll", query = "SELECT COUNT(t) FROM ClassTeacher t")
})
public class ClassTeacher implements Serializable {

    @Id
    @GeneratedValue
    private int teacherId;

    private String initials, firstname, lastname, title;

    @OneToOne(mappedBy = "teacher")
    private Classname classname;

}
