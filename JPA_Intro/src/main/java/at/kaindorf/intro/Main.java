package at.kaindorf.intro;

import at.kaindorf.intro.pojos.Address;
import at.kaindorf.intro.pojos.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_JPA_Intro");
        EntityManager em = emf.createEntityManager();

        Student student = new Student("5DHIF", 1L, "Nico", "Baumann", LocalDate.now());
        Address addr1 = new Address("Kaindorf", "Grazerstr", "220");
        //em.persist(addr1);
        student.setAddress(addr1);
        em.persist(student);

        Student student2 = new Student("5DHIF", 2L, "Adrian", "Berner", LocalDate.now().minusDays(1000));
        Address addr2 = new Address("Graz", "Herrengasse", "10");
        //em.persist(addr2);
        student2.setAddress(addr2);
        em.persist(student2);

        em.getTransaction().begin();
        em.getTransaction().commit();

        /*
        student2.setFirstname("Nikolaus"); //beim nächsten commit wird es übernommen

        em.getTransaction().begin();
        em.getTransaction().commit();
        */

        //em.detach(student); // aus dem persistence context raus machen
        //em.remove(student); // aus context raus und aus db löschen

        em.close();
        emf.close();
    }

}
