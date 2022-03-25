package at.kaindorf.pojos;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@XmlRootElement(name = "books")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Books {

    @XmlElement(name = "publisher")
    @XmlElementWrapper(name = "publisherlist")
    private List<Publisher> publishers;

}
