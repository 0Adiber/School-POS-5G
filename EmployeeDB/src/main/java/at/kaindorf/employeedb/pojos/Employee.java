package at.kaindorf.employeedb.pojos;

import at.kaindorf.employeedb.bl.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employees")
public class Employee implements Serializable {

    @Id
    @Column(name = "emp_no")
    @JsonProperty("emp_no")
    private int employeeNo;

    @Column(name = "first_name", length = 16, nullable = false)
    private String firstname;

    @Column(name = "last_name", length = 16, nullable = false)
    private String lastname;

    @Column(length = 1, nullable = false)
    private char gender;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("birthDate")
    @Column(name = "birth_date", nullable = false)
    private LocalDate dateOfBirth;

    @JsonIgnore
    @ManyToOne
    @Column(name = "dept_no", length = 4)
    private Department department;

}
