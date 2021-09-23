package at.kaindorf.customerdb.console;

import at.kaindorf.customerdb.json.CustomerDeserializer;
import at.kaindorf.customerdb.pojos.Address;
import at.kaindorf.customerdb.pojos.Country;
import at.kaindorf.customerdb.pojos.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class DataImport {

    EntityManagerFactory emf;
    EntityManager em;

    private void open() {
        emf = Persistence.createEntityManagerFactory("PU_CUSTOMERDB");
        em = emf.createEntityManager();
    }

    private void close() {
        em.close();
        emf.close();
    }

    private void importJSON() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        String path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "customers.json").toString();

        List<Customer> customers = mapper.readValue(new File(path), new TypeReference<List<Customer>>() {
        });

        importData(customers);

        TypedQuery<Number> query = em.createNamedQuery("Country.countAll", Number.class);
        System.out.println("Countries imported: " + query.getSingleResult());
        query = em.createNamedQuery("Address.countAll", Number.class);
        System.out.println("Addresses imported: " + query.getSingleResult());
        query = em.createNamedQuery("Customer.countAll", Number.class);
        System.out.println("Customers imported: " + query.getSingleResult());

    }

    private void importXML() {

    }

    private void importData(List<Customer> customers) {
        em.getTransaction().begin();
        customers.forEach(c -> em.persist(c));
        em.getTransaction().commit();
    }

    private void main() {
        open();
        try {
            importJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
    }

    public static void main(String[] args) {
        DataImport imp = new DataImport();
        imp.main();
    }

}
