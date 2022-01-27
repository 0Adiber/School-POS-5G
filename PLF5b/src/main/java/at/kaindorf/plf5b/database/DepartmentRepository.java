package at.kaindorf.plf5b.database;

import at.kaindorf.plf5b.pojos.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}