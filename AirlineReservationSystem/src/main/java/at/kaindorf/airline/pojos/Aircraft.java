package at.kaindorf.airline.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "aircraft")
@ToString(onlyExplicitlyIncluded = true)
@NamedQueries({
    @NamedQuery(
        name = "Aircraft.getCountriesOfAirports",
        query = "SELECT DISTINCT port.country FROM aircraft craft JOIN craft.airports port WHERE craft.id = :aircraftId ORDER BY port.country"
    )
})
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
    @NonNull
    private AircraftType aircraftType;

    @ManyToMany(mappedBy = "aircrafts")
    private List<Airport> airports = new ArrayList<>();;

    @OneToMany(mappedBy = "aircraft", orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();;
}
