package edu.iit.sat.itmd4515.vgangaswamy;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
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
    public  void beforeEach()  {
        //create a test data
        em = emf.createEntityManager();
        tx = em.getTransaction();
        Bakery product = new Bakery("Test Data", "A delicious ice cream cake with chocolate layers", ProductType.CAKES,99,18,true);
        tx.begin();
        em.persist(product);
        tx.commit();
        System.out.println("beforeEach with bake\t" + product.toString());
    }

    @Test
    public void createTest() {
        Bakery product = new Bakery("brioche", "A delicious bread", ProductType.BREAD,99,18,true);
        tx.begin();
        em.persist(product);
        tx.commit();

        Bakery readBackFromDatabase = em.find(Bakery.class, product.getId());
        Assertions.assertNotNull(readBackFromDatabase);
        Assertions.assertTrue(readBackFromDatabase.getId() > 0);
        Assertions.assertEquals("brioche", readBackFromDatabase.getName());
    }

    @Test
    public void readTest()  {
    }

    @Test
    public void updateTest() {
        // Fetch Bakery entity with name "Brioche"
        List<Bakery> resultList = em.createQuery("select b from Bakery b where b.name = 'brioche'", Bakery.class).getResultList();

        // Assert that Brioche exists in the database
        Assertions.assertFalse(resultList.isEmpty(), "No Bakery with name 'brioche' found");

        // Log message if Brioche is found
        Bakery product = resultList.get(0);
        System.out.println("Bakery item 'brioche' found with ID: " + product.getId());

        // Begin transaction and update the price
        tx.begin();
        product.setPrice(101.0F);  // Set the price to 101.0
        tx.commit();

        // Verify that the update was successful
        Bakery readBackFromDatabase = em.createQuery("select b from Bakery b where b.name = 'Brioche'", Bakery.class).getSingleResult();
        Assertions.assertEquals(product.getId(), readBackFromDatabase.getId());
        Assertions.assertEquals(101.0, readBackFromDatabase.getPrice(), 0.01);  // Added delta for floating-point comparison
    }


    @Test
    public void deleteTest() {
    }

    @AfterEach
    public  void afterEach() {
        Bakery product = em.createQuery("select b from Bakery b where b.name = 'Test Data'", Bakery.class).getSingleResult();
        System.out.println("afterEach with bake\t" + product.toString());
        tx.begin();
        em.remove(product);
        tx.commit();
        //remove a test data
        em.close();

    }

    @AfterAll
    public static void afterAll() {
        if (emf != null) {
            emf.close();
        }
    }
}
