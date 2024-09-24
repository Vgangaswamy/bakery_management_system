1. Paragraph that describes the business domain you have chosen to work with, and why?
- I chose to work on the domain of "Inventory Management," with a specifically Bakery Management System. During my undergraduate studies, I completed a project in Database Management Systems that was about Bakery Inventory Management. 
Given my prior experience and familiarity with this domain, I figured it would be an excellent choice for this project, allowing me to use my existing knowledge while learning new concepts.

2.  what other entities from your business domain can you think of? How might they relate to one another? You can answer this in narrative form, or you can answer it with a database diagram.
- In addition to the Bakery entity for Lab 4, I can think of several other entities that would be important in a Bakery Management System. For example,
**Ingredient** would represent the raw materials like flour and sugar that go into the bakery items, and each bakery item could have multiple ingredients. 
**Supplier** would represent the vendors that provide those ingredients. 
**Order** would capture customer orders, which could contain multiple bakery items, and each **Customer** could place several orders.
**Employee** would represent the staff working in the bakery, and they might be involved in preparing different items. Lastly, 
**Inventory** would track the stock of both bakery items and ingredients, helping the bakery manage its resources efficiently.
All of these entities would be related to each other in various ways, such as bakery items having multiple ingredients and customers placing multiple orders.

---------------------------------------------------------------------------------------------------------------------------------------------------------------

**BakeryJPATest**
1. BeforeEach, 
  //Before Each tests, I am creating a new "brioche" entity and logging it's creation
  ```
    public void beforeEach() {
          em = emf.createEntityManager();
          tx = em.getTransaction();
          Bakery brioche = new Bakery("brioche", "A delicious bread", ProductType.BREAD, 99, 18, true);
          tx.begin();
          em.persist(brioche);
          tx.commit();
          System.out.println("beforeEach: " + brioche.toString());
      }
  ```
2. After Each,
   // Delete everything from the table after each test
 ```
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
  ```
3. CreateTest,
   Here I am creating a new entity with name "sourdough" and then checking its existence in the database through assertions
 ```
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
```
4. ReadTest,
   Here I am reading the name "brioche" i create before each test and confirming its existence through assertions and logging
```
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
```
5. Update Test,
   Here I am updating the price and quantity of "brioche"
```
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
```
6. Delete test,
   Here, I am deleting the "brioche", this is the only test case where there will be no entities in the table to delete in "AfterEach"
```
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
```
The below screenshot shows the log for all the test cases respectively
![JPA test cases.png](Screenshots%2FJPA%20test%20cases.png)

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

JUnit test cases output screenshot(bean Validation)

![JUnit test cases ss.png](Screenshots%2FJUnit%20test%20cases%20ss.png)







   
   
