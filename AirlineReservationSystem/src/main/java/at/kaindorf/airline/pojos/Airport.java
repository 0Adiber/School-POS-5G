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
@Entity(name = "airport")
@ToString(onlyExplicitlyIncluded = true)
@NamedQueries({
    @NamedQuery(
        name = "Airport.aircraftOfAirlineLanding",
        query = "SELECT COUNT(flight.aircraft) FROM airport port JOIN port.arrivedFlights flight WHERE flight.arrivePort.id = :airportId AND flight.aircraft.airline.id = :airlineId"
    ),
    @NamedQuery(
        name = "Airport.allFlights",
        query = "SELECT COUNT(f1.aircraft) + COUNT(f2.aircraft) FROM airport port join port.arrivedFlights f1 join port.departedFlights f2 WHERE f1.arrivePort.id = :airportId OR f2.departurePort.id = :airportId"
    ),
    @NamedQuery(
            name = "Airport.airlinesOfAircrafts",
            query = "SELECT crafts.airline.name FROM airport port join port.aircrafts crafts"
    ),
})
public class Airport implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "airport_id")
    private Long id;

    @Column(length = 60)
    private String  country, name;

    @Column(length = 50)
    private String city;

    @ManyToMany
    @JoinTable(name = "aircraft_airport",
            joinColumns = { @JoinColumn(name="airport_id") },
            inverseJoinColumns = { @JoinColumn(name = "aircraft_id") })
    private List<Aircraft> aircrafts = new ArrayList<>();

    @OneToMany(mappedBy = "departurePort", orphanRemoval = true)
    private List<Flight> departedFlights = new ArrayList<>();;

    @OneToMany(mappedBy = "arrivePort", orphanRemoval = true)
    private List<Flight> arrivedFlights = new ArrayList<>();;

    public Airport(String line) {
        String[] parts = line.split("\"?,\"?");
        this.country = parts[8].trim();
        if(this.country.length() > 60) this.country = this.country.substring(0, 60);
        this.city = parts[10].trim();
        if(this.city.length() > 50) this.city = this.city.substring(0, 50);
        this.name = parts[3].trim();
        if(this.name.length() > 60) this.name = this.name.substring(0, 60);
    }

    public void addAircraft(Aircraft aircraft) {
        this.aircrafts.add(aircraft);
        aircraft.getAirports().add(this);
    }

}
