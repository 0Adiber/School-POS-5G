package at.kaindorf.airline;

import at.kaindorf.airline.bl.CSVImport;
import at.kaindorf.airline.db.DBAccess;
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

        /*
            IMPORT DATA
         */

        DBAccess.getInstance().connect();

        try {
            CSVImport.importData();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        /*
            TEST DATA
         */

        // TODO

        /*
            TEST QUERIES
         */
        EntityManager em = DBAccess.getInstance().getEm();

        TypedQuery<Number> tq = em.createNamedQuery("Airport.AircraftOfAirlineLanding", Number.class);
        tq.setParameter("airportId", 1);
        tq.setParameter("airlineId", 1);
        System.out.println(tq.getSingleResult().toString());

        DBAccess.getInstance().disconnect();
    }
}
