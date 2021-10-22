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
@Entity(name = "airline")
@IdClass(AirlinePK.class)
public class Airline implements Serializable {

    @Id
    @Column(name = "airline_id")
    private Long id;

    @Id
    @Column(name = "airline_name")
    private String name;

    @OneToMany(mappedBy = "airline")
    private List<Aircraft> aircrafts;

    @OneToMany(mappedBy = "airline")
    private List<Flight> flights;
}
