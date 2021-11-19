package at.kaindorf.schooldb.beans;

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
        @NamedQuery(name = "Classname.findByName", query = "SELECT c FROM Classname c WHERE c.name = :name"),
        @NamedQuery(name = "Classname.findAll", query = "SELECT c FROM Classname c"),
        @NamedQuery(name = "Classname.findByFloor", query = "SELECT c FROM Classname c WHERE c.room.floor = :floor"),
        @NamedQuery(name = "Classname.countAll", query = "SELECT COUNT(c) FROM Classname c")
})
public class Classname implements Serializable {

    @Id
    @GeneratedValue
    private int classId;

    private String name;

    private int grade, size;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "room")
    private Room room;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher")
    private ClassTeacher teacher;

    public void setRoom(Room room) {
        this.room = room;
        room.setClassname(this);
    }

    public void setTeacher(ClassTeacher teacher) {
        this.teacher = teacher;
        teacher.setClassname(this);
    }
}
