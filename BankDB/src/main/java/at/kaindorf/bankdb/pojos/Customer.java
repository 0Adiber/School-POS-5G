package at.kaindorf.bankdb.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findByCity", query = "SELECT c FROM Customer c WHERE UPPER(c.address.city) = UPPER(:city)"),
        @NamedQuery(name = "Customer.findByGender", query = "SELECT c FROM Customer c WHERE c.gender = :gender")
})
public class Customer implements Serializable {

    @Id
    @Column(name = "customer_id")
    @EqualsAndHashCode.Include
    private Long customerId;

    @Column(length = 100)
    private String firstname;

    @Column(length = 100)
    private String lastname;

    @Column(name = "customer_number")
    private Long customerNumber;

    @Column(name = "birthdate")
    private LocalDate dayOfBirth;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany
    @JoinTable(name = "customer_account", joinColumns = { @JoinColumn(name = "customer_id") }, inverseJoinColumns = { @JoinColumn(name = "account_id") })
    private List<Account> accounts;

}
