package at.kaindorf.customerdb.json;

import at.kaindorf.customerdb.pojos.Address;
import at.kaindorf.customerdb.pojos.Country;
import at.kaindorf.customerdb.pojos.Customer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class JsonCustomerDeserializer extends StdDeserializer<Customer> {

    public JsonCustomerDeserializer() {
        super(Customer.class);
    }

    Set<Country> countries = new HashSet<>();
    Set<Address> addresses = new HashSet<>();
    JsonNode node;

    @Override
    public Customer deserialize(JsonParser p, DeserializationContext dc) throws IOException {
        node = dc.readValue(p, JsonNode.class);

        //COUNTRY
        String countryCode = get("country_code");
        String countryName = get("country");
        Country country = new Country(countryName, countryCode);

        countries.add(country);

        Country finalCountry = country;
        country = countries.stream().filter(c -> c.equals(finalCountry)).findFirst().get();


        //ADDRESS
        String streetname = get("streetname");
        int streetnumber = Integer.parseInt(get("streetnumber"));
        String postalCode = get("postal_code");
        String city = get("city");
        Address address = new Address(streetname, streetnumber, postalCode, city, country);

        addresses.add(address);

        Address finalAddress = address;
        address = addresses.stream().filter(a -> (a.equals(finalAddress))).findFirst().get();

        country.addAddress(address);

        String firstname = get("firstname");
        String lastname = get("lastname");
        char gender = get("gender").charAt(0);
        boolean active = Boolean.parseBoolean(get("active"));
        String email = get("email");
        LocalDate since = LocalDate.parse(get("since"), Customer.DTF);
        Customer customer = new Customer(firstname, lastname, gender, active, email, since, address);

        address.addCustomer(customer);

        return customer;
    }

    private String get(String key) {
        return node.get(key).asText();
    }
}
