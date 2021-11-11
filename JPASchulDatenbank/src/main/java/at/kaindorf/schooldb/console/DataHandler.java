package at.kaindorf.schooldb.console;

import at.kaindorf.schooldb.beans.*;
import at.kaindorf.schooldb.bl.Floor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXB;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataHandler {

    private EntityManagerFactory emf;
    private EntityManager em;

    public void open() {
        close();
        emf = Persistence.createEntityManagerFactory("PU_SCHOOLDB");
        em = emf.createEntityManager();
    }

    public void close() {
        if(em == null || !em.isOpen())
            return;
        em.close();
        emf.close();
    }

    public void importTables() {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "schooldata.xml");
        School school = JAXB.unmarshal(path.toFile(), School.class);

        em.getTransaction().begin();

        for(TeacherAdapter ta : school.getTeachers()) {
            Classname cn = new Classname();
            cn.setName(ta.getClassname());
            cn.setSize(ta.getStudents());
            cn.setGrade(Integer.parseInt(ta.getClassname().substring(0,1)));

            Room room = new Room();
            room.setName(ta.getRoom());
            room.setFloor(ta.getRoom().charAt(0) == '1' ? Floor.GROUND : Floor.FIRST);

            ClassTeacher ct = new ClassTeacher();
            ct.setFirstname(ta.getFirstname());
            ct.setLastname(ta.getLastname());
            ct.setInitials(ta.getInitials());
            ct.setTitle(ta.getTitle());

            cn.setRoom(room);
            cn.setTeacher(ct);

            em.persist(cn);
        }

        em.getTransaction().commit();

        // PRINT RESULTS
        Query count = em.createNamedQuery("Room.countAll");
        Number rooms = (Number)count.getSingleResult();
        count = em.createNamedQuery("Classname.countAll");
        Number classes = (Number)count.getSingleResult();
        count = em.createNamedQuery("ClassTeacher.countAll");
        Number teachers = (Number)count.getSingleResult();

        System.out.println(String.format("rooms imported: %d\nclasses imported: %d\nteachers imported: %d", rooms, classes, teachers));
    }

}
