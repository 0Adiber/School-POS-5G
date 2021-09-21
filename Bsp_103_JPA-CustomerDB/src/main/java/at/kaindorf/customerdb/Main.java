package at.kaindorf.customerdb;

import at.kaindorf.customerdb.pojos.Address;
import at.kaindorf.customerdb.pojos.Country;
import at.kaindorf.customerdb.pojos.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_CUSTOMERDB");
        EntityManager em = emf.createEntityManager();

        Country country = new Country();
        Address address = new Address(1, country);
        Customer customer = new Customer('M', false);
        customer.setAddress(address);

        em.persist(customer);

        em.getTransaction().begin();
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}
