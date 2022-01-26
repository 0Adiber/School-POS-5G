package at.kaindorf.bankdb.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Address implements Serializable {

    @Id
    @Column(name = "address_id")
    private Long addressId;

    @Column(length = 100)
    private String streetname;

    @Column(name = "street_number", length = 10)
    private String streetNumber;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Column(length = 100)
    private String city;

    @OneToMany(mappedBy = "address")
    private List<Customer> customers;

}
