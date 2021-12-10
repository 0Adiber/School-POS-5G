package at.kaindorf.employeedb.pojos;

import at.kaindorf.employeedb.bl.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employees")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee implements Serializable {

    @Id
    @Column(name = "emp_no")
    @JsonProperty("emp_no")
    @EqualsAndHashCode.Include
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_no")
    private Department department;

}
