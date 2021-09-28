package at.kaindorf.intro;

import at.kaindorf.intro.pojos.Address;
import at.kaindorf.intro.pojos.SchoolClass;
import at.kaindorf.intro.pojos.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_JPA_Intro");
        EntityManager em = emf.createEntityManager();

        Student student = new Student("5DHIF", 1L, "Nico", "Baumann", LocalDate.now());
        Address addr1 = new Address("Kaindorf", "Grazerstr", "220");
        //em.persist(addr1);
        student.setAddress(addr1);
        addr1.setStudent(student);
        //em.persist(student);

        Student student2 = new Student("5DHIF", 2L, "Adrian", "Berner", LocalDate.now().minusDays(1000));
        Address addr2 = new Address("Graz", "Herrengasse", "10");
        //em.persist(addr2);
        student2.setAddress(addr2);
        addr2.setStudent(student2);
        //em.persist(student2);

        SchoolClass sc = new SchoolClass("5DHIF");
        sc.addStudent(student);
        sc.addStudent(student2);

        em.persist(sc);

        em.getTransaction().begin();
        em.getTransaction().commit();

        /*
        student2.setFirstname("Nikolaus"); //beim nächsten commit wird es übernommen

        em.getTransaction().begin();
        em.getTransaction().commit();
        */

        //em.detach(student); // aus dem persistence context raus machen
        //em.remove(student); // aus context raus und aus db löschen

        //JPQL-Queries
        TypedQuery<Student> typedQuery = em.createQuery("SELECT s FROM Student s", Student.class);

        List<Student> students = typedQuery.getResultList();

        System.out.println("Students----------------------------------------");
        students.stream().forEach(System.out::println);

        TypedQuery<Address> typedQuery2 = em.createNamedQuery("Address.GetAll", Address.class);
        typedQuery2.setParameter("street", "%gasse");
        List<Address> addresses = typedQuery2.getResultList();

        System.out.println("Adressen----------------------------------------");
        addresses.stream().forEach(System.out::println);

        TypedQuery<Address> typedQuery3 = em.createNamedQuery("Address.GetByClassname", Address.class);
        typedQuery3.setParameter("classname", "5DHIF");
        addresses = typedQuery2.getResultList();

        System.out.println("Adressen----------------------------------------");
        addresses.stream().forEach(System.out::println);

        Query query = em.createNamedQuery("Student.CountByClassname");
        Long number = (Long)query.getSingleResult();
        System.out.println("Students in 5DHIF: " + number);

        em.close();
        emf.close();
    }

}
