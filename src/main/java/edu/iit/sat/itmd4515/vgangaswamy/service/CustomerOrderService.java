package edu.iit.sat.itmd4515.vgangaswamy.service;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.CustomerOrder;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CustomerOrderService extends AbstractService<CustomerOrder>{
    public CustomerOrderService() {
        super(CustomerOrder.class);
    }
    public List<CustomerOrder> readAll() {
        try {
            List<CustomerOrder> orders = super.readAll("CustomerOrder.readAll");
            Logger.getLogger(CustomerOrderService.class.getName()).info("Successfully fetched orders");
            return orders;
        } catch (Exception e) {
            Logger.getLogger(CustomerOrderService.class.getName()).severe("Error fetching orders: " + e.getMessage());
            throw e; // Re-throw or handle the exception as needed
        }
    }
}
