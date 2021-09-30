package at.kaindorf.customerdb.pojos;

import at.kaindorf.customerdb.json.DateDeserializer;
import at.kaindorf.customerdb.xml.DateAdapter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor

public class CustomerDummy {

    private String country;

    @XmlElement(name = "country_code")
    @JsonProperty("country_code")
    private String countryCode;

    private String city;

    @XmlElement(name = "postal_code")
    @JsonProperty("postal_code")
    private String postalCode;

    private String streetname;

    private int streetnumber;

    private String firstname;

    private String lastname;

    private String gender;

    private boolean active;

    private String email;

    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate since;
}
