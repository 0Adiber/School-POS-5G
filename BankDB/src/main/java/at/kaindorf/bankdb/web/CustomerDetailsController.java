package at.kaindorf.bankdb.web;

import at.kaindorf.bankdb.database.AccountRepository;
import at.kaindorf.bankdb.database.CustomerRepository;
import at.kaindorf.bankdb.database.GiroAccountRepository;
import at.kaindorf.bankdb.pojos.Account;
import at.kaindorf.bankdb.pojos.Customer;
import at.kaindorf.bankdb.pojos.GiroAccount;
import at.kaindorf.bankdb.pojos.SavingsAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.TypedQuery;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

@Controller
@Slf4j
@RequestMapping("/details")
@SessionAttributes({"customer", "totalBalance"})
public class CustomerDetailsController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public String getDetails(@RequestParam(value="customer") Long customerId, Model model) {
        Customer customer = customerRepository.findById(customerId).get();
        model.addAttribute("customer", customer);

        model.addAttribute("totalBalance", accountRepository.getTotalBalance(customer.getCustomerId()));

        return "customerDetailsView";
    }

    @PostMapping("/update")
    public String updateAccount(@RequestParam(value="accountId") Long accountId, @RequestParam(value="amount") double amount, @RequestParam(value="action") int action, @ModelAttribute(value = "customer") Customer customer, Model model) {
        Account account = accountRepository.findById(accountId).get();
        amount = amount * action;
        if(account instanceof GiroAccount) {
            GiroAccount giro = (GiroAccount) account;
            if(giro.getBalance() + giro.getOverdraft() + amount < 0) {
                model.addAttribute("error", "Withdrawal of " + NumberFormat.getCurrencyInstance().format(amount * action) + " for account (" + account.getAccountNumber() +") not possible");
                return "customerDetailsView";
            }
        } else {
            if(account.getBalance() + amount < 0) {
                model.addAttribute("error", "Withdrawal of " + NumberFormat.getCurrencyInstance().format(amount * action) + " for account (" + account.getAccountNumber() +") not possible");
                return "customerDetailsView";
            }
        }

        account.setBalance(Math.round((account.getBalance()+amount)*100)/100.0);
        accountRepository.saveAndFlush(account);
        model.addAttribute("customer", customerRepository.findById(customer.getCustomerId()).get());

        return "customerDetailsView";
    }

}
