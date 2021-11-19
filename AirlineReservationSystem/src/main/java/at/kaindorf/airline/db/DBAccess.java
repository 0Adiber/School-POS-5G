package at.kaindorf.airline.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DBAccess {

    private static DBAccess db;
    private EntityManagerFactory emf;
    private EntityManager em;

    public static DBAccess getInstance() {
        if (db == null) {
            db = new DBAccess();
        }
        return db;
    }

    private DBAccess() {
    }

    public void connect() {
        if(emf == null || !emf.isOpen())
            emf = Persistence.createEntityManagerFactory("PU_AIRSYS");
        if(em == null || !em.isOpen())
            em = emf.createEntityManager();
    }

    public void disconnect() {
        if (em != null && em.isOpen()) em.close();
        if (emf != null && emf.isOpen()) emf.close();
    }

    public EntityManager getEm() {
        connect();
        return em;
    }

    public void persist(Object obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public void persistAll(List objs) {
        em.getTransaction().begin();
        objs.forEach(em::persist);
        em.getTransaction().commit();
    }

}
