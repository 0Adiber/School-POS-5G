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
@Entity(name = "aircraft_type")
public class AircraftType implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "aircraft_type_id")
    private Long id;

    @Column(name = "type_name")
    private String name;

    private int seats;

    @OneToMany(mappedBy = "aircraftType")
    private List<Aircraft> aircrafts;
}
