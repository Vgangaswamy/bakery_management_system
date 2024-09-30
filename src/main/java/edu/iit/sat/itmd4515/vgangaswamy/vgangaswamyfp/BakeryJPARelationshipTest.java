package edu.iit.sat.itmd4515.vgangaswamy.vgangaswamyfp;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Ingredient;
import edu.iit.sat.itmd4515.vgangaswamy.domain.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BakeryJPARelationshipTest extends AbstractJPATest {
    @Test
    public void uniDirectionalTestCase() {

        Ingredient ingredient = new Ingredient("Flour", 100);
        Inventory inventory = new Inventory("Flour Stock", 100, 10, ingredient);
        inventory.setIngredient(ingredient);

        tx.begin();
        em.persist(ingredient);
        em.persist(inventory);
        tx.commit();

        //Assertions
        Inventory readBackFromDatabase = em.find(Inventory.class, inventory.getId());
        Assertions.assertNotNull(readBackFromDatabase.getIngredient());
        Assertions.assertEquals("Flour", readBackFromDatabase.getIngredient().getName() );
    }

    @Test
    public void biDirectionalTestCase() {

    }

}
