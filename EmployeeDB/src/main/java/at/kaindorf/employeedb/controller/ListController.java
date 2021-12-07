package at.kaindorf.employeedb.controller;

import at.kaindorf.employeedb.pojos.Department;
import at.kaindorf.employeedb.pojos.Employee;
import at.kaindorf.employeedb.repo.DepartmentRepository;
import at.kaindorf.employeedb.repo.EmployeeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/employees")
public class ListController {

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    public ListController(@Autowired DepartmentRepository departmentRepository, @Autowired EmployeeRepository employeeRepository) {

        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;

        Path path = Path.of(System.getProperty("user.dir"), "src", "main", "resources", "employeedb.json");

        try {
            List<Department> departments = new ObjectMapper().readValue(path.toFile(), new TypeReference<List<Department>>(){});

            Set<Employee> uEmps = new HashSet<>();
            for(Department d : departments) {
                List<Employee> emps = d.getEmployees();
                d.setEmployees(new ArrayList<>());
                uEmps.add(d.getDeptManager());
                d.setManager(uEmps.stream().filter(f -> f.getEmployeeNo() == d.getDeptManager().getEmployeeNo()).findFirst().get());
                for(Employee e : emps) {
                    uEmps.add(e);
                    d.addEmployee(uEmps.stream().filter(f -> f.getEmployeeNo() == e.getEmployeeNo()).findFirst().get());
                }
            }

            departmentRepository.saveAll(departments);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        if(model.getAttribute("currentDept") == null) {
            model.addAttribute("employees", employeeRepository.findAllByOrderByLastnameAscFirstnameAsc());
        } else {
            model.addAttribute("employees", ((Department)model.getAttribute("currentDept")).getEmployees());
        }
    }

    /*
    @GetMapping
    public String showDesign() {
        return "listForm";
    }*/

    @RequestMapping(method = { RequestMethod.POST,  RequestMethod.GET })
    public String showList(@RequestParam(value = "currentDeptNo", required = false, defaultValue = "-1") String current, @ModelAttribute("currentDept") Department currentDept, Model model) {
        Department dept = currentDept;
        List<Employee> employees = null;

        try {
            if(dept.getDeptNo() == null)
                dept = departmentRepository.findById(current).get();
            employees = dept.getEmployees();
            employees.sort(Comparator.comparing(Employee::getLastname).thenComparing(Employee::getFirstname));
        } catch(NoSuchElementException | NullPointerException e) {
            dept = null;
            employees = employeeRepository.findAllByOrderByLastnameAscFirstnameAsc();
        } finally {
            model.addAttribute("employees", employees);
            model.addAttribute("currentDept", dept);
        }

        return "listForm";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("empNo") int empNo, RedirectAttributes attributes) {

        Employee emp = employeeRepository.findById(empNo).get();
        attributes.addFlashAttribute("currentDept", emp.getDepartment());
        emp.getDepartment().removeEmployee(emp);
        employeeRepository.saveAndFlush(emp);

        return "redirect:/employees";
    }

}
