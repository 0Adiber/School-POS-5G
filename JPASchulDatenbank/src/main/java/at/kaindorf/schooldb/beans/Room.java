package at.kaindorf.schooldb.beans;

import at.kaindorf.schooldb.bl.Floor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(name = "Room.findByClassName", query = "SELECT r FROM Room r WHERE r.classname = :classname"),
        @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
        @NamedQuery(name = "Room.FindByFloor", query = "SELECT r FROM Room r WHERE r.floor = :floor"),
        @NamedQuery(name = "Room.countAll", query = "SELECT COUNT(r) FROM Room r")
})
public class Room implements Serializable {

    @Id
    @GeneratedValue
    private int roomId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Floor floor;

    @OneToOne(mappedBy = "room")
    private Classname classname;
}
