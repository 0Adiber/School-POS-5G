package at.kaindorf.bl;

import at.kaindorf.pojos.Author;
import at.kaindorf.pojos.Book;
import at.kaindorf.pojos.Publisher;
import at.kaindorf.xml.XMLAccess;
import jakarta.xml.bind.JAXBException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            List<Publisher> publishers = XMLAccess.getInstance().loadbooks();

            // 1.
            System.out.println("Publishers: " + publishers.stream().map(Publisher::getName).sorted().collect(Collectors.joining(",")));
            // 2.
            System.out.println("Avg Price: " + publishers.stream().map(Publisher::getBooks).flatMap(Collection::stream).mapToDouble(Book::getPrice).average().getAsDouble());
            // 3.
            System.out.println("Sum Price: " + publishers.stream().map(Publisher::getBooks).flatMap(Collection::stream).mapToDouble(Book::getPrice).sum());
            // 4.
            Map<String, List<Book>> publisherBooks = publishers.stream().collect(Collectors.toMap(Publisher::getName, Publisher::getBooks));
            // 5.
            Map<String, List<Author>> publisherAuthors = publishers.stream().collect(Collectors.toMap(Publisher::getName, p -> p.getBooks().stream().map(Book::getAuthors).flatMap(Collection::stream).collect(Collectors.toList())));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
