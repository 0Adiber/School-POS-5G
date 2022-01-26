package at.kaindorf.bankdb.database;

import at.kaindorf.bankdb.pojos.Customer;
import at.kaindorf.bankdb.pojos.GiroAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiroAccountRepository extends JpaRepository<GiroAccount, Long> {

    List<GiroAccount> findByCustomersContains(Customer customer);
}