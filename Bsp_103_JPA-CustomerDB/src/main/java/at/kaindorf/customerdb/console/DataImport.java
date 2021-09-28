package at.kaindorf.customerdb.console;

import at.kaindorf.customerdb.pojos.Country;
import at.kaindorf.customerdb.pojos.Customer;
import at.kaindorf.customerdb.xml.XmlCustomerDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.persistence.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    }

    private void importXML() throws IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "customers.xml");
            doc = builder.parse(path.toFile());

            List<Customer> customers = new XmlCustomerDeserializer().deserialize(doc);
            importData(customers);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            throw new IOException("Error parsing customers.xml");
        }

    }

    private void importData(List<Customer> customers) {
        em.getTransaction().begin();
        customers.forEach(c -> em.persist(c));
        em.getTransaction().commit();

        TypedQuery<Number> query = em.createNamedQuery("Country.countAll", Number.class);
        System.out.println("Countries imported: " + query.getSingleResult());
        query = em.createNamedQuery("Address.countAll", Number.class);
        System.out.println("Addresses imported: " + query.getSingleResult());
        query = em.createNamedQuery("Customer.countAll", Number.class);
        System.out.println("Customers imported: " + query.getSingleResult());
    }

    private void main() {
        open();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n\n  /$$$$$$                        /$$                                                 /$$$$$$$  /$$$$$$$ \n" +
                " /$$__  $$                      | $$                                                | $$__  $$| $$__  $$\n" +
                "| $$  \\__/ /$$   /$$  /$$$$$$$ /$$$$$$    /$$$$$$  /$$$$$$/$$$$   /$$$$$$   /$$$$$$ | $$  \\ $$| $$  \\ $$\n" +
                "| $$      | $$  | $$ /$$_____/|_  $$_/   /$$__  $$| $$_  $$_  $$ /$$__  $$ /$$__  $$| $$  | $$| $$$$$$$ \n" +
                "| $$      | $$  | $$|  $$$$$$   | $$    | $$  \\ $$| $$ \\ $$ \\ $$| $$$$$$$$| $$  \\__/| $$  | $$| $$__  $$\n" +
                "| $$    $$| $$  | $$ \\____  $$  | $$ /$$| $$  | $$| $$ | $$ | $$| $$_____/| $$      | $$  | $$| $$  \\ $$\n" +
                "|  $$$$$$/|  $$$$$$/ /$$$$$$$/  |  $$$$/|  $$$$$$/| $$ | $$ | $$|  $$$$$$$| $$      | $$$$$$$/| $$$$$$$/\n" +
                " \\______/  \\______/ |_______/    \\___/   \\______/ |__/ |__/ |__/ \\_______/|__/      |_______/ |_______/ \n" +
                "                                                                                                        \n" +
                "                                                                                                        \n");

        GAMELOOP:
        while (true) {

            System.out.println();
            System.out.println("[1] => import from JSON file");
            System.out.println("[2] => import from XML file");
            System.out.println("--- Countries");
            System.out.println("[3] => #num of Countries");
            System.out.println("[4] => display ALL Countries");
            System.out.println("[5] => display Country by name");
            System.out.println("--- Addresses");
            System.out.println("[6] => #num of Addresses");
            System.out.println("--- Customer");
            System.out.println("[7] => #num of Customers");
            System.out.println("[8] => display all years");
            System.out.println("[9] => display Customers by Country");
            System.out.println("[q] --> to quit the program");
            System.out.print("Choice :> ");

            try {
                String input = reader.readLine().trim();
                char choice = input.length() > 0 ? input.charAt(0) : ' ';
                switch (choice) {
                    case '1':
                        importJSON();
                        break;
                    case '2':
                        importXML();
                        break;
                    case '3':
                        Query query = em.createNamedQuery("Country.countAll");
                        System.out.println("\nNumber of Countries: " + (Long)query.getSingleResult() + "\n");
                        break;
                    case '4':
                        TypedQuery<Country> c1 = em.createNamedQuery("Country.findAll", Country.class);
                        System.out.println("\nCountries: ");
                        c1.getResultStream().forEach(Country::pretty);
                        break;
                    case '5':
                        TypedQuery<Country> c2 = em.createNamedQuery("Country.findByName", Country.class);

                        System.out.println("Countryname :> ");
                        String cname = reader.readLine().trim();

                        c2.setParameter("name", cname);
                        System.out.print("\nName?: ");
                        c2.getResultStream().forEach(Country::pretty);
                        break;
                    case 'q':
                        break GAMELOOP;
                    default:
                        System.out.println("Invalid input\n");
                }
            } catch (IOException e) {
                System.out.println("Could not read from console!\nExiting...");
            }
        }

        close();
    }

    public static void main(String[] args) {
        DataImport imp = new DataImport();
        imp.main();
    }

}
