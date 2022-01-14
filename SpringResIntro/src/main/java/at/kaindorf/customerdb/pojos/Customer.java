package at.kaindorf.customerdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Character gender;

    @Column(name = "active", nullable = false)
    @NonNull
    private Boolean active;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "since")
    private LocalDate since;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    @ToString.Exclude
    @JsonIgnore
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
