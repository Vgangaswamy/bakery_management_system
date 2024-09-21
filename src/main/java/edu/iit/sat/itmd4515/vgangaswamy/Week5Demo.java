package edu.iit.sat.itmd4515.vgangaswamy;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Week5Demo {
    public static void main(String... args) {
        // Create an EntityManagerFactory using the persistence unit name
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515testPU");
        // Create an EntityManager
        EntityManager em = emf.createEntityManager();

        // Create a new Bakery object
        Bakery product = new Bakery(999L, "cake");

        // Get the transaction object
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();
            em.persist(product);
            tx.commit();

            System.out.println("Product saved successfully!");
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
