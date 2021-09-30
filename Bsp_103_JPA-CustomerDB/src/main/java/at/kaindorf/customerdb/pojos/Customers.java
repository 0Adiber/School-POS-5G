package at.kaindorf.customerdb.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Customers {
    @XmlElement(name = "customer")
    private List<CustomerDummy> customers;
}
