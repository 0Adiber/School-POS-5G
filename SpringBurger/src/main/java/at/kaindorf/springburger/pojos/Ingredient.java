package at.kaindorf.springburger.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient implements Serializable {
    @Id
    private String id;

    private String name;
    private Type type;

    public enum Type {
        PATTY, VEGGIE, CHEESE;
    }

}