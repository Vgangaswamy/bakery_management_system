package edu.iit.sat.itmd4515.vgangaswamy.vgangaswamyfp;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.ProductType;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

public class BakeryJPATest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

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

    @Test
    public void createTest() {
        // Creating a new Bakery item called "sourdough"
        Bakery product = new Bakery("sourdough", "A delicious sourdough bread", ProductType.BREAD, 120, 15, true);
        tx.begin();
        em.persist(product);
        tx.commit();

        // Read it back from the database
        Bakery readBackFromDatabase = em.find(Bakery.class, product.getId());
        Assertions.assertNotNull(readBackFromDatabase);
        Assertions.assertTrue(readBackFromDatabase.getId() > 0);
        Assertions.assertEquals("sourdough", readBackFromDatabase.getName());
    }

    @Test
    public void readTest() {
        // Finding the "brioche" Bakery item
        Bakery product = em.createQuery("select b from Bakery b where lower(b.name) = 'brioche'", Bakery.class)
                .getSingleResult();
        Assertions.assertNotNull(product, "Bakery item 'brioche' exists.");
        Assertions.assertEquals("brioche", product.getName(), "The name is 'brioche'.");
        Assertions.assertEquals(99, product.getPrice(), "The price is 99.");
        System.out.println("Read test: " + product.toString());
    }

    @Test
    // Updating the price and quantity of brioche
    public void updateTest() {
        // Finding the Bakery entity with the name "brioche" and logging its existence with ID
        Bakery productToUpdate = em.createQuery("select b from Bakery b where lower(b.name) = 'brioche'", Bakery.class)
                .getSingleResult();
        System.out.println("Found Bakery item 'brioche' with ID: " + productToUpdate.getId());

        // updating the price and quantity
        tx.begin();
        productToUpdate.setPrice(175.0F);
        productToUpdate.setQuantity(30);
        tx.commit();

        Bakery updatedProduct = em.find(Bakery.class, productToUpdate.getId());

        Assertions.assertEquals(175.0F, updatedProduct.getPrice(), 0.01, "The price is updated to 175.0");
        Assertions.assertEquals(30, updatedProduct.getQuantity(), "The quantity is updated to 30");

        System.out.println("Updated price for Bakery item 'brioche': " + updatedProduct.getPrice());
        System.out.println("Updated quantity for Bakery item 'brioche': " + updatedProduct.getQuantity());
    }

    @Test
    // Deleting brioche which is created BeforeEach
    public void deleteTest() {
        Bakery product = em.createQuery("select b from Bakery b where lower(b.name) = 'brioche'", Bakery.class)
                .getSingleResult();
        Assertions.assertNotNull(product, "Bakery item 'brioche' exists before deletion.");

        tx.begin();
        em.remove(product);
        tx.commit();

        List<Bakery> result = em.createQuery("select b from Bakery b where lower(b.name) = 'brioche'", Bakery.class)
                .getResultList();
        Assertions.assertTrue(result.isEmpty(), "Bakery item 'brioche' is deleted.");
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
