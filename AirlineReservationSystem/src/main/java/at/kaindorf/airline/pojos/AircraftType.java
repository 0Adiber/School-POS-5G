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

    @Column(name = "type_name", length = 50)
    private String name;

    private int seats;

    public AircraftType(String line) {
        String[] parts = line.split(",");
        this.name = parts[1].trim();
        if(this.name.length() > 50) this.name = this.name.substring(0,50);
        seats = Integer.parseInt(parts[8]);
    }
}
