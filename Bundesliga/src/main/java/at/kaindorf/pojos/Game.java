package at.kaindorf.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Spiel")
public class Game {
    @XmlAttribute(name = "Runde")
    private int round;
    @XmlElement(name="Mannschaft1")
    private String team1;
    @XmlElement(name="Mannschaft2")
    private String team2;
    @XmlElement(name="Tore1")
    private int goal1;
    @XmlElement(name="Tore2")
    private int goal2;

    public boolean isTeamPresent(String name) {
        return team1.equals(name) || team2.equals(name);
    }
}
