package at.kaindorf.bankdb.web;

import at.kaindorf.bankdb.database.AccountRepository;
import at.kaindorf.bankdb.database.CustomerRepository;
import at.kaindorf.bankdb.pojos.Account;
import at.kaindorf.bankdb.pojos.Customer;
import at.kaindorf.bankdb.pojos.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/statistics")
@SessionAttributes("city")
public class StatisticController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public String getStats(@RequestParam(value = "city", required = false, defaultValue = "") String city, Model model) {

        model.addAttribute("avgNegative", accountRepository.getAverageNegative());
        model.addAttribute("multipleCustomers", accountRepository.getNumberOfMultipleCustomers());

        Account highest = accountRepository.findFirstByOrderByBalanceDesc();
        model.addAttribute("highest", highest);

        Account lowest = accountRepository.findFirstByOrderByBalanceAsc();
        model.addAttribute("lowest", lowest);

        List<Customer> customers = customerRepository.findByCity(city);
        model.addAttribute("customers", customers);
        model.addAttribute("city", city);

        int males = customerRepository.findByGender(Gender.m).size();
        int females = customerRepository.findByGender(Gender.w).size();
        model.addAttribute("males", Math.round(males*1.0/(males+females)*100));
        model.addAttribute("females", Math.round(females*1.0/(males+females)*100));

        return "statisticView";
    }

}
