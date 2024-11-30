package edu.iit.sat.itmd4515.vgangaswamy.web;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.Customer;
import edu.iit.sat.itmd4515.vgangaswamy.domain.CustomerOrder;
import edu.iit.sat.itmd4515.vgangaswamy.service.BakeryService;
import edu.iit.sat.itmd4515.vgangaswamy.service.CustomerOrderService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@SessionScoped
public class CustomerController implements Serializable {

    private static final Logger LOG = Logger.getLogger(CustomerController.class.getName());

    @EJB
    private BakeryService bakeryService;

    @EJB
    private CustomerOrderService customerOrderService;

    private Customer customer;
    private CustomerOrder customerOrder;
    private List<Bakery> availableBakeryItems;

    private Bakery selectedBakeryItem; // For edit functionality

    public CustomerController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside CustomerController.postConstruct()");
        customer = new Customer(); // Initialize customer
        customerOrder = new CustomerOrder(); // Initialize customer order
        availableBakeryItems = bakeryService.readAll(); // Load bakery items for selection
    }

    // Add product to customer's order
    public void addProductToOrder(Bakery bakeryItem) {
        LOG.info("Adding product to customer order: " + bakeryItem);
        customerOrder.getBakeryItems().add(bakeryItem); // Add product to order
        customerOrder.setTotalPrice(customerOrder.getTotalPrice() + bakeryItem.getPrice()); // Update total price
    }

    // Edit product in customer's order
    public void editProduct(Bakery bakeryItem) {
        LOG.info("Editing product: " + bakeryItem);
        selectedBakeryItem = bakeryItem; // Store the product for editing
    }

    // Save changes after editing a product
    public void saveEditedProduct() {
        LOG.info("Saving edited product: " + selectedBakeryItem);
        for (int i = 0; i < customerOrder.getBakeryItems().size(); i++) {
            if (customerOrder.getBakeryItems().get(i).getId().equals(selectedBakeryItem.getId())) {
                customerOrder.getBakeryItems().set(i, selectedBakeryItem); // Update product details
                break;
            }
        }
        selectedBakeryItem = null; // Clear the selection after saving
    }

    // Delete product from customer's order
    public void deleteProduct(Bakery bakeryItem) {
        LOG.info("Deleting product from customer order: " + bakeryItem);
        customerOrder.getBakeryItems().remove(bakeryItem); // Remove product from order
        customerOrder.setTotalPrice(customerOrder.getTotalPrice() - bakeryItem.getPrice()); // Update total price
    }

    // Save or confirm the customer order
    public String saveCustomerOrder() {
        LOG.info("Saving customer order: " + customerOrder);
        customerOrder.setCustomer(customer); // Associate the order with the customer
        customerOrderService.create(customerOrder); // Persist the order
        return "orderConfirmation.xhtml"; // Redirect to confirmation page
    }

    // Getters and setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public List<Bakery> getAvailableBakeryItems() {
        return availableBakeryItems;
    }

    public Bakery getSelectedBakeryItem() {
        return selectedBakeryItem;
    }

    public void setSelectedBakeryItem(Bakery selectedBakeryItem) {
        this.selectedBakeryItem = selectedBakeryItem;
    }
}


