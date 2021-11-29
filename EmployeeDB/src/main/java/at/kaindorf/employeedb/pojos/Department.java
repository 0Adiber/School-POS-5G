package at.kaindorf.employeedb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "departments")
public class Department implements Serializable {

    @Id
    @Column(name = "dept_no", length = 4)
    @JsonProperty("number")
    private int deptNo;

    @Column(name = "dept_name", length = 40, nullable = false)
    @JsonProperty("name")
    private String deptName;

    @OneToOne
    @JoinColumn(name = "emp_no", nullable = false)
    @JsonIgnore
    private Employee deptManager;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setDepartment(this);
    }

}
