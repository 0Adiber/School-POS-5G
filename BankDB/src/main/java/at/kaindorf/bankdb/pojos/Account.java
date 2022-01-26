package at.kaindorf.bankdb.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import at.kaindorf.bankdb.pojos.Customer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING, length = 31)
@NamedQueries({
        @NamedQuery(name = "Account.getTotalBalance", query = "SELECT SUM(a.balance) FROM Account a JOIN a.customers c WHERE c.customerId = :customerId"),
        @NamedQuery(name = "Account.getAverageNegative", query = "SELECT AVG(a.balance) FROM Account a WHERE a.balance < 0"),
        @NamedQuery(name = "Account.getNumberOfMultipleCustomers", query = "SELECT COUNT(a) FROM Account a WHERE a.customers.size > 1")
})
public abstract class Account implements Serializable {

    @Id
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number")
    private Long accountNumber;

    private Double balance;

    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customers;

}
