package at.kaindorf.springburger.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Burger implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must have 5 characters")
    private String name;

    @NotNull // geht auf den inhalt, nicht die liste selbst
    @Size(min = 2, message = "Choose at least two ingredients")
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne
    private Order order;

}
