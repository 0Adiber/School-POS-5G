package at.kaindorf.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
//@DiscriminatorValue("truck")
public class Truck extends Vehicle{
    private double payload;
    private Integer height;

    public Truck(@NonNull String brand, double payload, Integer height) {
        super(brand);
        this.payload = payload;
        this.height = height;
    }
}
