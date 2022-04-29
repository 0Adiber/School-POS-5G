package at.kaindorf.pojos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Publisher {

    private int publisherId;
    private String name, url;

    @XmlElement(name = "book")
    @XmlElementWrapper(name = "booklist")
    private List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return getPublisherId() == publisher.getPublisherId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPublisherId());
    }
}
