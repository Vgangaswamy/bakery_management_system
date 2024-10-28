package edu.iit.sat.itmd4515.vgangaswamy.service;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.CustomerOrder;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class CustomerOrderService extends AbstractService<CustomerOrder>{
    public CustomerOrderService() {
        super(CustomerOrder.class);
    }
    public List<CustomerOrder> readAll() {
        return super.readAll("CustomerOrder.readAll");
    }
}
