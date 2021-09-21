package at.kaindorf.intro.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor //für JPA braucht man parameterlosen constructor
@AllArgsConstructor
@RequiredArgsConstructor // alle nonnull sachen
@Entity(name = "student") //"name" kann man weg lassen
@IdClass(StudentPK.class)
public class Student implements Serializable {
    /*
    @Id
    @Column(name = "student_id")
    @GeneratedValue//(strategy = GenerationType.SEQUENCE)
    private Long studentId;
     */

    @Id
    @NonNull
    private String className;
    @Id
    @NonNull
    private Long catNo;

    @Column(nullable = false, length = 100)
    @Basic(fetch = FetchType.LAZY) // lädt firstname erst dann, wenn getter aufgerufen wird
    @NonNull
    private String firstname;

    @Column(nullable = false, length = 150)
    @NonNull
    private String lastname;

    @Column(name = "date_of_birth")
    @NonNull
    private LocalDate dateOfBirth;

    @Transient // nicht in db speichern
    private String fullname;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true) // dann braucht man kein em.persist(addr) mehr
    @JoinColumn(name = "address")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "school_class")
    private SchoolClass schoolClass;

    public String getFullname() {
        return String.format("%s %s", lastname, firstname);
    }
}
