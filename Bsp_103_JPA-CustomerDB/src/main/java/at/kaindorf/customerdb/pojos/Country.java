package at.kaindorf.customerdb.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity(name = "country")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Country.countAll", query = "SELECT COUNT(c) FROM country c"),
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM country c"),
    @NamedQuery(name = "Country.findByName", query = "SELECT c FROM country c WHERE upper(c.countryName) = upper(:name)"),
})
public class Country implements Serializable {
    @Id
    @Column(name = "country_id")
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private Long countryId;

    @Column(name = "name", length = 50)
    @NonNull
    private String countryName;

    @Column(name = "code", length = 10)
    @NonNull
    private String countryCode;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void pretty(){
        System.out.println(String.format("• [%s] %s", countryCode, countryName));
    }
}
