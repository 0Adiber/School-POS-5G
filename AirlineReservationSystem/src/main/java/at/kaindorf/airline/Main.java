package at.kaindorf.airline;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

        em.close();
        emf.close();
    }
}
