package edu.iit.sat.itmd4515.vgangaswamy.service;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.Customer;
import jakarta.ejb.Stateless;



import java.util.List;

@Stateless
public class CustomerService extends AbstractService<Customer> {
    public CustomerService() {
        super(Customer.class);
    }
    public List<Customer> readAll() {
        return super.readAll("Customer.readAll");
    }
}
