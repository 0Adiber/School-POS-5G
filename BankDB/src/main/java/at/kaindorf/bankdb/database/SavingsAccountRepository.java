package at.kaindorf.bankdb.database;

import at.kaindorf.bankdb.pojos.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}