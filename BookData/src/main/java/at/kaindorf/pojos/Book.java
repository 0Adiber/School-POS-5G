package at.kaindorf.pojos;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    private int bookId;
    private String title, url, isbn;
    private double price;

    @XmlElement(name = "author")
    @XmlElementWrapper(name = "authorlist")
    private List<Author> authors;

    @XmlTransient
    private Publisher publisher;

}
