package at.kaindorf.bankdb.database;

import at.kaindorf.bankdb.pojos.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Double getTotalBalance(Long customerId);

    Account findByAccountNumber(Long accountNumber);

    Double getAverageNegative();

    int getNumberOfMultipleCustomers();

    Account findFirstByOrderByBalanceDesc();

    Account findFirstByOrderByBalanceAsc();

}