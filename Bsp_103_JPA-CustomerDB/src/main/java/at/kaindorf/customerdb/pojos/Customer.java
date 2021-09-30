package at.kaindorf.customerdb.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@Entity(name = "customer")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Customer.countAll", query = "SELECT COUNT(c) FROM customer c"),
        @NamedQuery(name = "Customer.findYears", query = "SELECT DISTINCT EXTRACT(year from c.since) FROM customer c ORDER BY EXTRACT(year from c.since)"),
        @NamedQuery(name = "Customer.findFromCountry", query = "SELECT c FROM customer c WHERE UPPER(c.address.country.countryName) = UPPER(:country) OR UPPER(c.address.country.countryCode) = UPPER(:country)")
})
public class Customer implements Serializable {

    public static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

    @Id
    @Column(name = "customer_id")
    @GeneratedValue
    private Long customerId;

    @Column(name = "firstname", length = 100)
    private String firstname;

    @Column(name = "lastname", length = 100)
    private String lastname;

    @Column(name = "gender", length = 1, nullable = false)
    @NonNull
    private char gender;

    @Column(name = "active", nullable = false)
    @NonNull
    private boolean active;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "since")
    private LocalDate since;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    @ToString.Exclude
    private Address address;

    public Customer(String firstname, String lastname, @NonNull char gender, @NonNull boolean active, String email, LocalDate since, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.active = active;
        this.email = email;
        this.since = since;
        this.address = address;
    }

    public void pretty() {
        System.out.println(String.format("• %s %s", lastname.toUpperCase(), firstname));
    }
}
