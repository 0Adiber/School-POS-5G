package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "aircraft")
public class Aircraft implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "aircraft_id")
    private Long id;

    @ManyToOne
    @ToString.Exclude
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "airline_type_id")
    private AircraftType aircraftType;

    @ManyToMany(mappedBy = "aircrafts")
    private List<Airport> airports;

    @OneToMany(mappedBy = "aircraft", orphanRemoval = true)
    private List<Flight> flights;
}
