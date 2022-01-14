package at.kaindorf.customerdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity(name = "address")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private Long addressId;

    @Column(name = "street_name", length = 100)
    private String streetName;

    @Column(name = "street_number", nullable = false)
    @NonNull
    private Integer streetNumber;

    @Column(name = "postal_code", length = 50)
    private String postalCode;

    @Column(name = "city", length = 100)
    private String city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country", nullable = false)
    @NonNull
    @ToString.Exclude
    private Country country;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Customer> customers = new ArrayList<>();

    public Address(String streetName, @NonNull int streetNumber, String postalCode, String city, @NonNull Country country) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return streetNumber == address.streetNumber && Objects.equals(streetName, address.streetName) && Objects.equals(postalCode, address.postalCode) && Objects.equals(city, address.city) && country.equals(address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, streetNumber, postalCode, city, country);
    }
}
