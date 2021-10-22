package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "airport")
public class Airport implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "airport_id")
    private Long id;

    private String country, city, name;

    @ManyToMany
    @JoinTable(name = "aircraft_airport",
            joinColumns = { @JoinColumn(name="airport_id") },
            inverseJoinColumns = { @JoinColumn(name = "aircraft_id") })
    private List<Aircraft> aircrafts;

    @OneToMany(mappedBy = "departurePort")
    private List<Flight> departedFlights;

    @OneToMany(mappedBy = "arrivePort")
    private List<Flight> arrivedFlights;

}
