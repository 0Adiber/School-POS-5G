package at.kaindorf.customerdb.pojos;

import at.kaindorf.customerdb.json.CustomerDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "customer")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = CustomerDeserializer.class)
@NamedQueries({
        @NamedQuery(name = "Customer.countAll", query = "SELECT COUNT(c) FROM customer c"),
        @NamedQuery(name = "Customer.findYears", query = "SELECT DISTINCT c.since FROM customer c"),
        @NamedQuery(name = "Customer.findFromCountry", query = "SELECT c FROM customer c WHERE c.address.country.countryId = :countryId")
})
public class Customer {

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
}
