package at.kaindorf.airline.pojos;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "flight")
@ToString(onlyExplicitlyIncluded = true)
@NamedQueries({
    @NamedQuery(
        name = "Flight.getFlightsByAircraftType",
        query = "SELECT flight FROM flight flight WHERE UPPER(flight.aircraft.aircraftType.name) LIKE UPPER(:aircraftType)"
    ),
    @NamedQuery(
        name = "Flight.getFlightsDepartedInCountry",
        query = "SELECT flight FROM flight flight WHERE UPPER(flight.departurePort.country) LIKE UPPER(:country)"
    ),
    @NamedQuery(
        name = "Flight.getFlightsArrivedInCountry",
        query = "SELECT flight FROM flight flight WHERE UPPER(flight.arrivePort.country) LIKE UPPER(:country)"
    ),
})
public class Flight {

    @Id
    @GeneratedValue
    @Column(name = "flight_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "aircraft_id")
    @NonNull
    private Aircraft aircraft;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @NonNull
    private Airline airline;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "departure_airport")
    @NonNull
    private Airport departurePort;

    @Column(name = "departure_time")
    @NonNull
    private LocalTime departureTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "arrival_airport")
    @NonNull
    private Airport arrivePort;

    @Column(name = "arrival_time")
    @NonNull
    private LocalTime arrivalTime;
}
