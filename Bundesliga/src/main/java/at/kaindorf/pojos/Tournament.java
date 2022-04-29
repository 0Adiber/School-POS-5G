package at.kaindorf.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Meisterschaft")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tournament {
    @XmlAttribute(name = "Name")
    private String name;
    @XmlElementWrapper(name = "Spiele")
    @XmlElement(name = "Spiel")
    private List<Game> games = new ArrayList<>();
}
