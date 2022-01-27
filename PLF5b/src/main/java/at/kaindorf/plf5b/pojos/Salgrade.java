package at.kaindorf.plf5b.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "salgrade")
@ToString(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(name = "Salgrade.getGrade", query = "SELECT s.gradeId FROM salgrade s JOIN emp e ON e.salary BETWEEN s.losal AND s.hisal WHERE e.empNo = :empNo")
})
public class Salgrade implements Serializable {

    @Id
    @Column(name = "grade")
    private Integer gradeId;

    private Integer losal, hisal;

}
