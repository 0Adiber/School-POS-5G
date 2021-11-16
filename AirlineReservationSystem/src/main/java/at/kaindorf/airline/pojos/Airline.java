package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "airline")
@IdClass(AirlinePK.class)
@ToString(onlyExplicitlyIncluded = true)
@NamedQueries({
    @NamedQuery(
        name = "Airline.countAllAircrafts",
        query = "SELECT COUNT(craft) FROM airline line JOIN line.aircrafts craft WHERE line.id = :airlineId"
    ),
    @NamedQuery(
        name = "Airline.getAircraftByFlight",
        query = "SELECT flight.aircraft.id FROM airline line JOIN line.flights flight WHERE line.id = :airlineId AND flight.id = :flightId"
    ),
    @NamedQuery(
        name = "Airline.getAircraftTypes",
        query = "SELECT DISTINCT craft.aircraftType.name FROM airline line JOIN line.aircrafts craft WHERE line.id = :airlineId"
    )
})
public class Airline implements Serializable {

    @Id
    @Column(name = "airline_id")
    private Long id;

    @Id
    @Column(name = "airline_name", length = 40)
    private String name;

    @OneToMany(mappedBy = "airline")
    private List<Aircraft> aircrafts = new ArrayList<>();;

    @OneToMany(mappedBy = "airline", orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();;

    public Airline(String line) {
        String[] parts = line.split("\"?,\"?");
        this.id = Long.parseLong(parts[0].trim());
        this.name = parts[1].trim();
        if(this.name.length() > 40) this.name = this.name.substring(0,40);
    }

    public void addAircraft(Aircraft aircraft) {
        this.aircrafts.add(aircraft);
        aircraft.setAirline(this);
    }
}
