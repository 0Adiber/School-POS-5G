package at.kaindorf.bankdb.web;

import at.kaindorf.bankdb.database.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/customers")
public class CustomerSelectionController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String listCustomers(@RequestParam(value = "lastname", required = false, defaultValue = "") String lastname, Model model) {
        model.addAttribute("customers", customerRepository.findAllByLastnameContainingIgnoreCaseOrderByLastnameAscFirstnameAsc(lastname));
        model.addAttribute("currentLastname", lastname);
        log.info(lastname);
        return "customerSelectionView";
    }



}
