package at.kaindorf.employeedb.repo;

import at.kaindorf.employeedb.pojos.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}