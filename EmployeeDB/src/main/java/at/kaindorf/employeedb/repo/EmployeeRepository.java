package at.kaindorf.employeedb.repo;

import at.kaindorf.employeedb.pojos.Department;
import at.kaindorf.employeedb.pojos.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    List<Employee> findAllByOrderByLastnameAscFirstnameAsc();

    Employee findFirstByOrderByEmployeeNoDesc();

    List<Employee> findAllByDepartment(Department dept);
    
}