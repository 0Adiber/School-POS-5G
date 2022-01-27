package at.kaindorf.plf5b.pojos;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "emp")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee implements Serializable, Comparable<Employee> {

    @Id
    @Column(name = "empno")
    @EqualsAndHashCode.Include
    private Integer empNo;

    @Column(name = "ename", length = 50)
    private String lastname;

    @Column(length = 30)
    private String job;

    @Column(name = "sal")
    @Min(value = 700, message = "Salary must be between 700 and 9999")
    @Max(value = 9999, message = "Salary must be between 700 and 9999")
    private Integer salary;

    private Integer comm;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate hiredate;

    @ManyToOne
    @JoinColumn(name = "mgr")
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "deptno")
    private Department deptno;

    @Override
    public int compareTo(Employee o) {
        if(lastname.equals(o.getLastname())) {
            return empNo - o.getEmpNo();
        }
        else return lastname.compareTo(o.getLastname());
    }
}
