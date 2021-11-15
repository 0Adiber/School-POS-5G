package at.kaindorf.airline.bl;

import at.kaindorf.airline.db.DBAccess;
import at.kaindorf.airline.pojos.AircraftType;
import at.kaindorf.airline.pojos.Airline;
import at.kaindorf.airline.pojos.Airport;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVImport {

    public static void importData() {
        Path base = Paths.get(System.getProperty("user.dir"), "src", "main", "resources");

        List<AircraftType> types = new ArrayList<>();
        try {
            types = Files.lines(base.resolve("aircrafttypes.csv")).skip(1).map(AircraftType::new).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading AircraftTypes");
        }

        List<Airline> airlines = new ArrayList<>();
        try {
            airlines = Files.lines(base.resolve("airlines.csv")).skip(1).map(Airline::new).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading Airlines");
        }

        List<Airport> airports = new ArrayList<>();
        try {
            airports = Files.lines(base.resolve("airports.csv")).skip(1).map(Airport::new).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading Airports");
        }

        DBAccess.getInstance().persistAll(types);
        DBAccess.getInstance().persistAll(airlines);
        DBAccess.getInstance().persistAll(airports);
    }

}
