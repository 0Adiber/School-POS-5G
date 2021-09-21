package at.kaindorf.intro.pojos;

import lombok.*;

import javax.persistence.*;
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

    @OneToOne(mappedBy = "address") //damit man in der datenbank nicht nochmal foreign key hat, der darauf verweist
    private Student student;

}
