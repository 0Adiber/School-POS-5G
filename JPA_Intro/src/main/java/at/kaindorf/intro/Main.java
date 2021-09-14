package at.kaindorf.intro;

import at.kaindorf.intro.pojos.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_JPA_Intro");
        EntityManager em = emf.createEntityManager();

        Student student = new Student("Nico", "Baumann", LocalDate.now());

        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}
