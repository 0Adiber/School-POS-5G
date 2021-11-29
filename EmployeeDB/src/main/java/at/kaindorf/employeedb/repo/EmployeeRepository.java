package at.kaindorf.employeedb.repo;

import at.kaindorf.employeedb.pojos.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}