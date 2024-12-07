package edu.iit.sat.itmd4515.vgangaswamy.web;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.Customer;
import edu.iit.sat.itmd4515.vgangaswamy.domain.CustomerOrder;
import edu.iit.sat.itmd4515.vgangaswamy.service.BakeryService;
import edu.iit.sat.itmd4515.vgangaswamy.service.CustomerOrderService;
import edu.iit.sat.itmd4515.vgangaswamy.service.CustomerService;
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
    private CustomerService customerService;

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
        LOG.info("saveCustomerOrder() invoked.");

        // Validate Customer Data
        validateCustomer();

        // Persist Customer if new
        if (customer.getId() == null) {
            saveNewCustomer();
        }

        // Persist CustomerOrder
        saveCustomerOrderToDatabase();

        // Redirect to confirmation page
        return "/customerOrder/OrderConfirmation.xhtml?faces-redirect=true";
    }

    private void validateCustomer() {
        if (customer.getName() == null || customer.getName().isBlank()) {
            LOG.severe("Customer name is invalid: " + customer.getName());
            throw new IllegalArgumentException("Customer name must not be blank.");
        }
        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            LOG.severe("Customer email is invalid: " + customer.getEmail());
            throw new IllegalArgumentException("Customer email must not be blank.");
        }
        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isBlank()) {
            LOG.severe("Customer phone number is invalid: " + customer.getPhoneNumber());
            throw new IllegalArgumentException("Customer phone number must not be blank.");
        }
    }

    private void saveNewCustomer() {
        try {
            LOG.info("Persisting new customer: " + customer);
            customerService.create(customer);
            LOG.info("Customer persisted successfully.");
        } catch (Exception e) {
            LOG.severe("Error persisting customer: " + e.getMessage());
            throw e;
        }
    }

    private void saveCustomerOrderToDatabase() {
        customerOrder.setCustomer(customer); // Associate customer with the order
        LOG.info("CustomerOrder details: " + customerOrder);

        if (customerOrder.getBakeryItems() == null || customerOrder.getBakeryItems().isEmpty()) {
            LOG.severe("CustomerOrder has no items.");
            throw new IllegalArgumentException("CustomerOrder must have at least one item.");
        }

        try {
            customerOrderService.create(customerOrder);
            LOG.info("CustomerOrder persisted successfully.");
        } catch (Exception e) {
            LOG.severe("Error persisting CustomerOrder: " + e.getMessage());
            throw e;
        }
    }

    public List<Bakery> getCustomerOrderItems() {
        return customerOrder.getBakeryItems();
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

    public String goToCart() {
        return "/customerOrder/cart.xhtml"; // Specify the path to the cart page
    }
}


