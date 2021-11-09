package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "flight")
public class Flight {

    @Id
    @GeneratedValue
    @Column(name = "flight_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Airline airline;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "departure_airport")
    private Airport departurePort;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "arrival_airport")
    private Airport arrivePort;

    @Column(name = "arrival_name")
    private LocalTime arrivalTime;
}
