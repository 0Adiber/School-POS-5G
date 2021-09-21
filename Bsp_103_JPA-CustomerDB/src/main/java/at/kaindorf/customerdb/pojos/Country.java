package at.kaindorf.customerdb.pojos;

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
    private Long countryId;

    @Column(name = "name", length = 50)
    @NonNull
    private String countryName;

    @Column(name = "code", length = 10)
    @NonNull
    private String countryCode;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();
}
