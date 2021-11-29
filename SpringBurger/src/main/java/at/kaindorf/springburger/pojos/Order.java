package at.kaindorf.springburger.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "burger_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "City is required")
    private String city;

    @OneToMany(mappedBy="order")
    @ToString.Exclude
    private List<Burger> burgers = new ArrayList<>();

    public void addBurger(Burger burger) {
        burgers.add(burger);
        burger.setOrder(this);
    }

}
