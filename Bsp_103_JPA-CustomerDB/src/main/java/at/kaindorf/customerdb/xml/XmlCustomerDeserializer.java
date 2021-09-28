package at.kaindorf.customerdb.xml;

import at.kaindorf.customerdb.pojos.Address;
import at.kaindorf.customerdb.pojos.Country;
import at.kaindorf.customerdb.pojos.Customer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class XmlCustomerDeserializer {

    Set<Country> countries = new HashSet<>();
    Set<Address> addresses = new HashSet<>();

    public List<Customer> deserialize(Document doc) {
        List<Customer> customers = new ArrayList<>();
        NodeList list = doc.getDocumentElement().getElementsByTagName("customer");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (!(node instanceof Element)) continue;
            Element el = (Element) node;

            // COUNTRY
            Country country = new Country(get(el, "country"), get(el, "country_code"));
            countries.add(country);
            Country finalCountry = country;
            country = countries.stream().filter(c -> c.equals(finalCountry)).findFirst().get();

            // ADDRESS
            Address address = new Address(
                    get(el,"streetname"),
                    Integer.parseInt(get(el,"streetnumber")),
                    get(el,"postal_code"),
                    get(el,"city"),
                    country
            );
            addresses.add(address);
            Address finalAddress = address;
            address = addresses.stream().filter(a -> a.equals(finalAddress)).findFirst().get();
            country.addAddress(address);

            // CUSTOMER
            Customer customer = new Customer(
                    get(el,"firstname"),
                    get(el,"lastname"),
                    get(el,"gender").charAt(0),
                    Boolean.parseBoolean(get(el, "active")),
                    get(el,"email"),
                    LocalDate.parse(get(el,"since"), Customer.DTF),
                    address
            );
            address.addCustomer(customer);

            customers.add(customer);
        }
        return customers;
    }

    public static String get(Element el, String elementName) {
        return el.getElementsByTagName(elementName).item(0).getTextContent();
    }

}
