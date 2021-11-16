package at.kaindorf.airline.bl;

import at.kaindorf.airline.db.DBAccess;
import at.kaindorf.airline.pojos.*;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CSVImport {

    public static void importData() {
        Path base = Paths.get(System.getProperty("user.dir"), "src", "main", "resources");
        Random rand = new Random();

        final List<AircraftType> types = new ArrayList<>();
        try {
            types.addAll(Files.lines(base.resolve("aircrafttypes.csv")).skip(1).map(AircraftType::new).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading AircraftTypes");
        }

        DBAccess.getInstance().persistAll(types);

        final List<Airline> airlines = new ArrayList<>();
        try {
            airlines.addAll(Files.lines(base.resolve("airlines.csv")).skip(1).map(Airline::new).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading Airlines");
        }

        DBAccess.getInstance().persistAll(airlines);

        final List<Airport> airports = new ArrayList<>();
        try {
            airports.addAll(Files.lines(base.resolve("airports.csv")).skip(1).map(Airport::new).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading Airports");
        }

        DBAccess.getInstance().persistAll(airports);

        final List<Aircraft> aircrafts = new ArrayList<>();
        for(int i = 0; i<1000; i++) {
            Aircraft aircraft = new Aircraft(types.get(rand.nextInt(types.size())));
            aircrafts.add(aircraft);
            airlines.get(rand.nextInt(airlines.size())).addAircraft(aircraft);
        }

        DBAccess.getInstance().persistAll(aircrafts);

        final List<Flight> flights = aircrafts.stream().map(aircraft -> {
            LocalTime departure = LocalTime.now().minus(rand.nextInt(60*60*24), ChronoUnit.SECONDS);
            LocalTime arrival = LocalTime.now().minus(rand.nextInt(60*60*24), ChronoUnit.SECONDS);

            Airport depart = airports.get(rand.nextInt(airports.size()));
            Airport arrive = airports.get(rand.nextInt(airports.size()));

            Flight f = new Flight(aircraft, aircraft.getAirline(), depart, departure, arrive, arrival);

            aircraft.getFlights().add(f);
            aircraft.getAirline().getFlights().add(f);

            depart.addAircraft(aircraft);
            depart.getDepartedFlights().add(f);

            arrive.addAircraft(aircraft);
            arrive.getArrivedFlights().add(f);

           return f;
        }).collect(Collectors.toList());

        DBAccess.getInstance().persistAll(flights);
    }

}
