package at.kaindorf.plf5b.database;

import at.kaindorf.plf5b.pojos.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}