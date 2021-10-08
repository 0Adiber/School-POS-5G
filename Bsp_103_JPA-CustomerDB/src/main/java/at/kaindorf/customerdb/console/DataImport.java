package at.kaindorf.customerdb.console;

import at.kaindorf.customerdb.pojos.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataImport {

    EntityManagerFactory emf;
    EntityManager em;

    private void open() {
        close();
        emf = Persistence.createEntityManagerFactory("PU_CUSTOMERDB");
        em = emf.createEntityManager();
    }

    private void close() {
        if(em != null && em.isOpen()) {
            em.close();
            emf.close();
        }
    }

    private void importJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "customers.json");
        List<CustomerDummy> customers = mapper.readValue(path.toFile(), new TypeReference<List<CustomerDummy>>() {
        });
        importData(customers);
    }

    private void importXML() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "customers.xml");
        Customers customers = JAXB.unmarshal(path.toFile(), Customers.class);
        importData(customers.getCustomers());
    }

    private void importData(List<CustomerDummy> customers) {
        em.getTransaction().begin();

        Set<Country> countries = new HashSet<>();
        Set<Address> addresses = new HashSet<>();

        for (CustomerDummy dummy : customers) {
            // COUNTRY
            Country country = new Country(dummy.getCountry(), dummy.getCountryCode());
            countries.add(country);
            Country finalCountry = country;
            country = countries.stream().filter(c -> c.equals(finalCountry)).findFirst().get();

            // ADDRESS
            Address address = new Address(dummy.getStreetname(), dummy.getStreetnumber(), dummy.getPostalCode(), dummy.getCity(), country);
            addresses.add(address);
            Address finalAddress = address;
            address = addresses.stream().filter(a -> a.equals(finalAddress)).findFirst().get();
            country.addAddress(address);

            // CUSTOMER
            Customer customer = new Customer(dummy.getFirstname(), dummy.getLastname(), dummy.getGender().charAt(0), dummy.isActive(), dummy.getEmail(), dummy.getSince(), address);
            address.addCustomer(customer);

            em.persist(customer);
        }

        em.getTransaction().commit();

        TypedQuery<Number> query = em.createNamedQuery("Country.countAll", Number.class);
        System.out.println("\nCountries imported: " + query.getSingleResult());
        query = em.createNamedQuery("Address.countAll", Number.class);
        System.out.println("Addresses imported: " + query.getSingleResult());
        query = em.createNamedQuery("Customer.countAll", Number.class);
        System.out.println("Customers imported: " + query.getSingleResult());
    }

    private void main() {
        //open();

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
                        System.out.println("Opening connection...");
                        open();
                        importJSON();
                        break;
                    case '2':
                        System.out.println("Opening connection...");
                        open();
                        importXML();
                        break;
                    case '3':
                        if(em == null || !em.isOpen()) {
                            System.out.println("\nPlease import data first!");
                            break;
                        }
                        Query c0 = em.createNamedQuery("Country.countAll");
                        System.out.println("\nNumber of Countries: " + (Long) c0.getSingleResult() + "\n");
                        break;
                    case '4':
                        if(em == null || !em.isOpen()) {
                            System.out.println("\nPlease import data first!");
                            break;
                        }
                        TypedQuery<Country> c1 = em.createNamedQuery("Country.findAll", Country.class);
                        System.out.println("\nCountries: ");
                        c1.getResultStream().forEach(Country::pretty);
                        break;
                    case '5':
                        if(em == null || !em.isOpen()) {
                            System.out.println("\nPlease import data first!");
                            break;
                        }
                        TypedQuery<Country> c2 = em.createNamedQuery("Country.findByName", Country.class);

                        System.out.print("Countryname :> ");
                        String cname = reader.readLine().trim();

                        c2.setParameter("name", cname);
                        System.out.println("\nName: ");
                        c2.getResultStream().forEach(Country::pretty);
                        break;
                    case '6':
                        if(em == null || !em.isOpen()) {
                            System.out.println("\nPlease import data first!");
                            break;
                        }
                        Query a1 = em.createNamedQuery("Address.countAll");
                        System.out.println("\nNumber of Addresses: " + (Long) a1.getSingleResult() + "\n");
                        break;
                    case '7':
                        if(em == null || !em.isOpen()) {
                            System.out.println("\nPlease import data first!");
                            break;
                        }
                        Query cu0 = em.createNamedQuery("Customer.countAll");
                        System.out.println("\nNumber of Customers: " + (Long) cu0.getSingleResult() + "\n");
                        break;
                    case '8':
                        if(em == null || !em.isOpen()) {
                            System.out.println("\nPlease import data first!");
                            break;
                        }
                        TypedQuery<Number> cu1 = em.createNamedQuery("Customer.findYears", Number.class);
                        System.out.println("\nYears: ");
                        cu1.getResultStream().forEach(y -> System.out.println("• " + y.intValue()));
                        break;
                    case '9':
                        if(em == null || !em.isOpen()) {
                            System.out.println("\nPlease import data first!");
                            break;
                        }
                        TypedQuery<Customer> cu2 = em.createNamedQuery("Customer.findFromCountry", Customer.class);
                        System.out.print("Countryname :> ");
                        String cname2 = reader.readLine().trim();
                        cu2.setParameter("country", cname2);
                        cu2.getResultStream().forEach(Customer::pretty);
                        break;
                    case 'q':
                        break GAMELOOP;
                    default:
                        System.out.println("Invalid input\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
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
