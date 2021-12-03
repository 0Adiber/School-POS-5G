package at.kaindorf.employeedb;

import at.kaindorf.employeedb.pojos.Department;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
public class EmployeeDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDbApplication.class, args);
    }

}
