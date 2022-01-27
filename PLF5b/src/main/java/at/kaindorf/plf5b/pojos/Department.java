package at.kaindorf.plf5b.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "dept")
@ToString(onlyExplicitlyIncluded = true)
public class Department implements Serializable {

    @Id
    private Integer deptno;

    @Column(length = 50)
    private String dname;

    @Column(name = "loc", length = 50)
    private String location;

    @OneToMany(mappedBy = "deptno")
    private List<Employee> employeeList;

}
