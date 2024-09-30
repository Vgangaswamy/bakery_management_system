package edu.iit.sat.itmd4515.vgangaswamy.vgangaswamyfp;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class AbstractJPATest {
    private static EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;

    @BeforeAll
    public static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    @BeforeEach
    //Before Each I am creating a new "brioche" entity and logging it's creation
    public void beforeEach() {
        em = emf.createEntityManager();
        tx = em.getTransaction();

        Bakery brioche = new Bakery("brioche", "A delicious bread", ProductType.BREAD, 99, 18, true);

        tx.begin();
        em.persist(brioche);
        tx.commit();

        System.out.println("beforeEach: " + brioche.toString());
    }

    @AfterEach
    // Delete everything from the table after each test
    public void afterEach() {
        tx.begin();
        int deletedCount = em.createQuery("DELETE FROM Bakery").executeUpdate();
        tx.commit();

        if (deletedCount > 0) {
            System.out.println("afterEach: Deleted " + deletedCount + " entries from the Bakery table.");
        } else {
            System.out.println("afterEach: No entries found in the Bakery table to delete.");
        }
        em.close();
    }

    @AfterAll
    public static void afterAll() {
        if (emf != null) {
            emf.close();
        }
    }
}
