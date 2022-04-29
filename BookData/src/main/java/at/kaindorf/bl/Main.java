package at.kaindorf.bl;

import at.kaindorf.pojos.Author;
import at.kaindorf.pojos.Book;
import at.kaindorf.pojos.Publisher;
import at.kaindorf.xml.XMLAccess;
import jakarta.xml.bind.JAXBException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
            //6.
            Scanner scanner = new Scanner(System.in);
            System.out.print("Val für 6tens: 2");
            int val = scanner.nextInt();
            List<Book> bookAuthorAnz = publishers.stream().map(Publisher::getBooks).flatMap(Collection::stream).filter(b -> b.getAuthors().size() == val).collect(Collectors.toList());
            //7.
            List<Book> bookNoUrl = publishers.stream().map(Publisher::getBooks).flatMap(Collection::stream).filter(b -> b.getUrl().isEmpty()).collect(Collectors.toList());
            //8.
            publishers.stream().map(Publisher::getBooks).flatMap(Collection::stream).filter(b -> b.getPrice() >= 30).forEach(b -> b.setPrice(b.getPrice()-5));
            //9.
            String[] authors = publishers.stream().map(Publisher::getBooks).flatMap(Collection::stream).map(Book::getAuthors).flatMap(Collection::stream).distinct() //get distinct authors
                    .filter(a -> a.getBooks().stream().map(Book::getPublisher).distinct().count() > 1) //more than 1 publisher distinct
                    .map(Author::getLastname).collect(Collectors.toList()).toArray(String[]::new); //get lastname and to array
            System.out.println("Authors: " + String.join("", authors));
            //10.
            AtomicInteger i = new AtomicInteger(1);
            List<Publisher> nPub = new ArrayList<>(publishers);
            nPub.forEach(p -> p.setPublisherId(i.getAndIncrement()));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
