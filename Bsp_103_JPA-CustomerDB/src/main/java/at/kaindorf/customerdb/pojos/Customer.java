package at.kaindorf.customerdb.pojos;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "customer")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue
    private Long countryId;

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
}
