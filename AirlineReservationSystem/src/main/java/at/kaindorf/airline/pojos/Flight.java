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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Airline airline;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_airport")
    private Airport departurePort;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_airport")
    private Airport arrivePort;

    @Column(name = "arrival_name")
    private LocalTime arrivalTime;
}
