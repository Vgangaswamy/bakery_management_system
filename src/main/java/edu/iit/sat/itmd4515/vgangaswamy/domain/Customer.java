package edu.iit.sat.itmd4515.vgangaswamy.domain;

import edu.iit.sat.itmd4515.vgangaswamy.security.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Customer class represents a customer in the system.
 * It includes attributes such as the customer's ID, name, email, and phone number.
 */
@Entity
@NamedQuery(name = "Customer.readAll", query = "select c from Customer c")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    /**
     * OneToMany bidirectional relationship
     * CustomerOrder is the owning side
     * customer is the inverse side
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerOrder> orders = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     * Default constructor (required by JPA).
     */
    public Customer() {
    }

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the ID of the customer.
     *
     * @return the customer's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the customer.
     *
     * @param id the customer's ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the customer.
     *
     * @return the customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name the customer's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the customer.
     *
     * @return the customer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the customer.
     *
     * @param email the customer's email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return the customer's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phoneNumber the customer's phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        if(this.id == null || customer.id == null) {
            return false;
        }
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Returns a string representation of the Customer object.
     *
     * @return a string containing the customer details
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", orders=" + orders.size() + " orders" +
                '}';
    }

}
