package at.kaindorf.plf5b.web;

import at.kaindorf.plf5b.database.DepartmentRepository;
import at.kaindorf.plf5b.database.EmployeeRepository;
import at.kaindorf.plf5b.database.SalgradeRepository;
import at.kaindorf.plf5b.pojos.Department;
import at.kaindorf.plf5b.pojos.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

@Controller
@Slf4j
@RequestMapping("/scottdb")
@SessionAttributes({"deptno", "emps"})
public class ScottController {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    SalgradeRepository salgradeRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    Random RAND = new Random();

    @ModelAttribute
    public void addDepts(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
    }

    @GetMapping
    public String getDepartments(Model model) {
        model.addAttribute("deptno", -1);
        model.addAttribute("emps", new TreeMap<>());
        return "scottView";
    }

    @PostMapping
    public String getEmployees(@RequestParam(value = "deptno", required = false, defaultValue = "-1") int deptno, Model model) {
        Department department = departmentRepository.findById(deptno).get();

        TreeMap<Employee, Integer> emps = new TreeMap<>();

        for(Employee e : department.getEmployeeList()) {
            Integer grade = salgradeRepository.getGrade(e.getEmpNo());
            emps.put(e, grade);
        }

        model.addAttribute("emps", emps);
        model.addAttribute("deptno", deptno);
        model.addAttribute("newEmp", new Employee());

        return "scottView";
    }

    @PostMapping("/addEmp")
    public String addEmp(@Valid @ModelAttribute("newEmp") Employee emp, Errors errors, @ModelAttribute("deptno") int deptno) {

        if(!errors.hasErrors()) {
            emp.setEmpNo(RAND.nextInt());
            emp.setDeptno(departmentRepository.findById(deptno).get());
            employeeRepository.save(emp);
            return "redirect:/scottdb";
        }

        return "scottView";
    }

}
