package at.kaindorf.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
//@DiscriminatorValue("car")
public class Car extends Vehicle {
    private String engine;
    private Integer seats;

    public Car(@NonNull String brand, String engine, Integer seats) {
        super(brand);
        this.engine = engine;
        this.seats = seats;
    }
}
