package at.kaindorf.employeedb.dto;

import at.kaindorf.employeedb.bl.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class EmployeeDto {
    @Size(min = 1, max = 14, message = "Firstname must have 1-14 characters")
    private String firstname;

    @Size(min = 1, max = 16, message = "Lastname must have 1-16 characters")
    private String lastname;

    @NotNull
    private char gender;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
