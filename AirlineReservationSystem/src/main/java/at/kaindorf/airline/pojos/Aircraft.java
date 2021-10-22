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
@Entity(name = "aircraft")
public class Aircraft implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "aircraft_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_type_id")
    private AircraftType aircraftType;

    @ManyToMany(mappedBy = "aircrafts")
    private List<Airport> airports;

    @OneToMany(mappedBy = "aircraft")
    private List<Flight> flights;
}
