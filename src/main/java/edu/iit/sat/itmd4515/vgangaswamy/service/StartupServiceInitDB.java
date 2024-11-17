package edu.iit.sat.itmd4515.vgangaswamy.service;

import edu.iit.sat.itmd4515.vgangaswamy.domain.*;
import edu.iit.sat.itmd4515.vgangaswamy.security.Group;
import edu.iit.sat.itmd4515.vgangaswamy.security.GroupService;
import edu.iit.sat.itmd4515.vgangaswamy.security.User;
import edu.iit.sat.itmd4515.vgangaswamy.security.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;
import java.util.logging.Logger;


@Startup
@Singleton
public class StartupServiceInitDB {

    private static final Logger LOG = Logger.getLogger(StartupServiceInitDB.class.getName());
    @EJB
    CustomerService cusSvc;

    @EJB
    BakeryService bakSVC;

    @EJB
    InventoryService invSVC;

    @EJB
    IngredientService ingSVC;

    @EJB
    CustomerOrderService cusOrSVC;

    @EJB
    UserService userSvc;

    @EJB
    GroupService groupSvc;

    public StartupServiceInitDB() {
    }

    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside StartupServiceInitDB.postConstruct()");

        Group customerGroup = new Group("CUSTOMER_GROUP","Group of bakery product customers");
        Group bakeryGroup = new Group("BAKERY_GROUP","Group of Bakeries");
        Group adminGroup = new Group("ADMIN_GROUP","Group of super admins ");
        groupSvc.create(customerGroup);
        groupSvc.create(bakeryGroup);
        groupSvc.create(adminGroup);

        User bak1 = new User("bakery1", "bakery1");
        bak1.addGroup(bakeryGroup);
        bak1.addGroup(adminGroup);

        User bak2 = new User("bakery2", "bakery2");
        bak2.addGroup(bakeryGroup);
        bak2.addGroup(customerGroup);


        User bak3 = new User("bakery3", "bakery3");
        bak3.addGroup(bakeryGroup);


        User cus1 = new User("cus1", "cus1");
        cus1.addGroup(customerGroup);
        User cus2 = new User("cus2", "cus2");
        cus2 .addGroup(customerGroup);


        User admin = new User("admin","admin");
        admin.addGroup(adminGroup);

        userSvc.create(bak1);
        userSvc.create(bak2);
        userSvc.create(bak3);
        userSvc.create(cus1);
        userSvc.create(cus2);
        userSvc.create(admin);


        Customer c1 = new Customer("nithish", "nithish@mail.com", "3126958456");
        c1.setUser(cus1);
        Customer c2 = new Customer("vidya","vidya@mail.com", "3125958456");
        c2.setUser(cus2);
        Customer c3 = new Customer("harsh", "harsh@mail.com", "3166958456");
        c3.setUser(bak3);

        cusSvc.create(c1);
        cusSvc.create(c2);
        cusSvc.create(c3);

        Bakery b1 = new Bakery("cupcake","cake in a shape of cup", ProductType.CAKES,99,100,true);
        b1.setUser(cus1);
        Bakery b2 = new Bakery("fruitcake","cake made of fruits", ProductType.CAKES,56,100,true);
        b2.setUser(cus2);
        Bakery b3 = new Bakery("berrycake","cake made of berries", ProductType.CAKES,69,100,true);
        b3.setUser(bak1);

        bakSVC.create(b1);
        bakSVC.create(b2);
        bakSVC.create(b3);

        Inventory v1 = new Inventory("flour_inventory",100,25);
        Inventory v2 = new Inventory("eggs_inventory",100,25);
        Inventory v3 = new Inventory("butter_inventory",100,25);

        invSVC.create(v1);
        invSVC.create(v2);
        invSVC.create(v3);

        Ingredient i1 = new Ingredient("flour", 1000);
        i1.setInventory(v1);
        Ingredient i2 = new Ingredient("eggs", 2000);
        i2.setInventory(v2);
        Ingredient i3 = new Ingredient("butter", 500);
        i3.setInventory(v3);

        ingSVC.create(i1);
        ingSVC.create(i2);
        ingSVC.create(i3);

        CustomerOrder o1 = new CustomerOrder(LocalDate.now(),99.00, c1);
        CustomerOrder o2 = new CustomerOrder(LocalDate.now(),100.00, c2);
        CustomerOrder o3 = new CustomerOrder(LocalDate.now(),69.00, c3);
        b1.addCustomerOrders(o1);
        b1.addCustomerOrders(o2);
        b1.addCustomerOrders(o3);

        cusOrSVC.create(o1);
        cusOrSVC.create(o2);
        cusOrSVC.create(o3);

        LOG.info("-----------------------------------------------------------------------------------------------");
        LOG.info("-----------------------------------------------Demo---------------------------------------------");
        for(Bakery b : bakSVC.readAll()) {
            LOG.info(b.toString());
            LOG.info("-----------------------------------------------------------------------------------------------");
            LOG.info(b.getCustomerOrders().toString());
            LOG.info("-----------------------------------------------------------------------------------------------");
        }

        LOG.info("-----------------------------------------------------------------------------------------------");
        LOG.info("-----------------------------------------------Demo---------------------------------------------");
        for(Customer c : cusSvc.readAll()) {
            LOG.info(c.toString());
            LOG.info("-----------------------------------------------------------------------------------------------");
            LOG.info(c.getOrders().toString());
            LOG.info("-----------------------------------------------------------------------------------------------");
        }

        LOG.info("-----------------------------------------------------------------------------------------------");
        LOG.info("-----------------------------------------------Demo---------------------------------------------");
        for(Inventory v : invSVC.readAll()) {
            LOG.info(v.toString());
            LOG.info("-----------------------------------------------------------------------------------------------");
            LOG.info(v.getIngredient().toString());
            LOG.info("-----------------------------------------------------------------------------------------------");
        }

        LOG.info("-----------------------------------------------------------------------------------------------");
        LOG.info("-----------------------------------------------Demo---------------------------------------------");
        for(Ingredient i : ingSVC.readAll()) {
            LOG.info(i.toString());
            LOG.info("-----------------------------------------------------------------------------------------------");
            LOG.info(i.getInventory().toString());
            LOG.info("-----------------------------------------------------------------------------------------------");
        }

        LOG.info("-----------------------------------------------------------------------------------------------");
        LOG.info("-----------------------------------------------Demo---------------------------------------------");
        for(CustomerOrder o : cusOrSVC.readAll()) {
            LOG.info(o.toString());
            LOG.info("-----------------------------------------------------------------------------------------------");
            LOG.info(o.getCustomer().toString());
            LOG.info("-----------------------------------------------------------------------------------------------");
        }
    }
}
