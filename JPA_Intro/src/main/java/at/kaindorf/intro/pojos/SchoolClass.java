package at.kaindorf.intro.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity//(name = "school_class")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "SchoolClass.GetByStudentName", query = "SELECT s FROM SchoolClass s JOIN s.students stu WHERE stu.lastname = :lastname")
})
public class SchoolClass implements Serializable {

    @Id
    @Column(length = 10, name = "school_classname")
    @NonNull
    private String schoolClassname;

    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("lastname desc, firstname asc")
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if(!students.contains(student)) {
            student.setSchoolClass(this);
            students.add(student);
        }
    }
}
