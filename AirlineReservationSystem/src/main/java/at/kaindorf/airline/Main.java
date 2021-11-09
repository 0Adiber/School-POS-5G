package at.kaindorf.airline;

import at.kaindorf.airline.pojos.Aircraft;
import at.kaindorf.airline.pojos.Airline;
import at.kaindorf.airline.pojos.Airport;
import at.kaindorf.airline.pojos.Flight;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n" +
                "           _____   _____ \n" +
                "     /\\   |  __ \\ / ____|\n" +
                "    /  \\  | |__) | (___  \n" +
                "   / /\\ \\ |  _  / \\___ \\ \n" +
                "  / ____ \\| | \\ \\ ____) |\n" +
                " /_/    \\_\\_|  \\_\\_____/ \n" +
                "                         \n" +
                "                         \n");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_AIRSYS");
        EntityManager em = emf.createEntityManager();

        /*
            TEST DATA
         */
        em.getTransaction().begin();

        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("Flughafen Graz");

        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);

        Airline airline = new Airline();
        airline.setId(1L);
        airline.setName("Airbus");
        airline.addAircraft(aircraft);

        Flight flight = new Flight();
        flight.setId(1L);
        flight.setAirline(airline);
        flight.setAircraft(aircraft);
        flight.setArrivePort(airport);

        em.persist(flight);

        em.getTransaction().commit();

        /*
            TEST QUERIES
         */

        TypedQuery<Number> tq = em.createNamedQuery("Airport.AircraftOfAirlineLanding", Number.class);
        tq.setParameter("airportId", 1);
        tq.setParameter("airlineId", 1);
        System.out.println(tq.getSingleResult().toString());

        em.close();
        emf.close();
    }
}
