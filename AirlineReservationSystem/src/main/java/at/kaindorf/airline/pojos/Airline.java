package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "airline")
@IdClass(AirlinePK.class)
public class Airline implements Serializable {

    @Id
    @Column(name = "airline_id")
    private Long id;

    @Id
    @Column(name = "airline_name", length = 40)
    private String name;

    @OneToMany(mappedBy = "airline")
    private List<Aircraft> aircrafts;

    @OneToMany(mappedBy = "airline", orphanRemoval = true)
    private List<Flight> flights;

    public Airline(String line) {
        String[] parts = line.split("\"?,\"?");
        this.id = Long.parseLong(parts[0].trim());
        this.name = parts[1].trim();
        if(this.name.length() > 40) this.name = this.name.substring(0,40);
    }

    public void addAircraft(Aircraft aircraft) {
        if(this.aircrafts == null)
            this.aircrafts = new ArrayList<>();
        this.aircrafts.add(aircraft);
        aircraft.setAirline(this);
    }
}
