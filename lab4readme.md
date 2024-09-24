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
1. BeforeEach
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
3. After Each
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
   
   
