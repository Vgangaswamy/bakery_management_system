package edu.iit.sat.itmd4515.vgangaswamy;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Week5Demo {
    public static void main(String... args) {

        Bakery product = new Bakery("Ice Cream Cake", "A delicious ice cream cake with chocolate layers.", ProductType.CAKES);

        System.out.println("Before persists: " + product.toString());
        // Create an EntityManagerFactory using the persistence unit name
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515testPU");
        // Create an EntityManager
        EntityManager em = emf.createEntityManager();



        // Get the transaction object
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(product);
            product.setName("Ice cake");
            tx.commit();
            System.out.println("After persists: " + product.toString());
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
