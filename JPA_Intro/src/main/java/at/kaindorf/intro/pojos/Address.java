package at.kaindorf.intro.pojos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long addressId;

    @NonNull
    @Column(length = 100, nullable = false)
    private String city;
    @NonNull
    @Column(length = 100, nullable = false)
    private String street;
    @NonNull
    @Column(length = 100, nullable = false)
    private String number;

}
