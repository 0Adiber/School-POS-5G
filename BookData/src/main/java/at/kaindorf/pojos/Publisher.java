package at.kaindorf.pojos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Publisher {

    private int publisherId;
    private String name, url;

    @XmlElement(name = "book")
    @XmlElementWrapper(name = "booklist")
    private List<Book> books;

}
