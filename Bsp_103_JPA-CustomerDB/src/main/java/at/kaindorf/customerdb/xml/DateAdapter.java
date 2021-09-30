package at.kaindorf.customerdb.xml;

import at.kaindorf.customerdb.pojos.Customer;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        try {
            return LocalDate.parse(s, Customer.DTF);
        }catch(DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return Customer.DTF.format(localDate);
    }
}
