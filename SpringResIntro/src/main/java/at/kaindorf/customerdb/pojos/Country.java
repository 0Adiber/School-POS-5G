package at.kaindorf.customerdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "country")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void pretty(){
        System.out.println(String.format("• [%s] %s", countryCode, countryName));
    }
}
