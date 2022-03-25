package at.kaindorf.xml;

import at.kaindorf.pojos.Author;
import at.kaindorf.pojos.Book;
import at.kaindorf.pojos.Books;
import at.kaindorf.pojos.Publisher;
import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class XMLAccess {

    private static XMLAccess instance;
    private static Path file = Path.of("src", "main", "resources", "books.xml");

    public static XMLAccess getInstance() {
        if(instance == null)
            instance = new XMLAccess();
        return instance;
    }

    private XMLAccess() {}

    public List<Publisher> loadbooks() throws JAXBException {
        List<Publisher> publishers = (JAXB.unmarshal(file.toFile(), Books.class)).getPublishers();

        List<Book> books = new ArrayList<>();//publishers.stream().map(Publisher::getBooks).flatMap(Collection::stream).collect(Collectors.toList());

        for(Publisher p : publishers) {
            for(Book b : p.getBooks()) {
                b.setPublisher(p);
                books.add(b);
            }
        }

        Set<Author> authors = new HashSet<>();

        for(Book b : books) {
            List<Author> temp = new ArrayList<>();
            for(Author a : b.getAuthors()) {
                authors.add(a);
                Author fa = authors.stream().filter(aa -> aa.getAuthorId() == a.getAuthorId()).findFirst().get();
                fa.getBooks().add(b);
                temp.add(fa);
            }
            b.setAuthors(temp);
        }

        return publishers;
    }

}
