package edu.iit.sat.itmd4515.vgangaswamy.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The CustomerOrder class represents an order placed by a customer.
 * It contains information such as the order ID, date, and total price.
 */
@Entity
@NamedQuery(name = "CustomerOrder.readAll", query = "select o from CustomerOrder o")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FutureOrPresent
    private LocalDate date;
    @Min(0)
    private double totalPrice;

    /**
     * ManyToMany bidirectional relationship
     * CustomerOrder is the owning side
     * Bakery is the inverse side
     */
    @ManyToMany
    @JoinTable(
            name = "Order_Bakery",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "bakery_id")
    )

    private List<Bakery> bakeryItems = new ArrayList<>();


    /**
     * ManyToOne bidirectional relationship
     * CustomerOrder is the owning side
     * customer is the inverse side
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Default constructor (required by JPA).
     */
    public CustomerOrder() {
    }

    /**
     * Constructor to initialize the date and total price of the order.
     *
     * @param date       the date when the order was placed
     * @param totalPrice the total price of the order
     */
    public CustomerOrder(LocalDate date, double totalPrice, Customer customer) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }

    /**
     * Gets the ID of the order.
     *
     * @return the ID of the order
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the date of the order.
     *
     * @return the date of the order
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the order.
     *
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the total price of the order.
     *
     * @return the total price of the order
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the order.
     *
     * @param totalPrice the total price to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Bakery> getBakeryItems() {
        return bakeryItems;
    }

    public void setBakeryItems(List<Bakery> bakeryItems) {
        this.bakeryItems = bakeryItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerOrder)) return false;
        CustomerOrder that = (CustomerOrder) o;
        if(this.id == null || that.id == null) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Returns a string representation of the CustomerOrder object.
     *
     * @return a string containing the order details
     */

    /**
     * Returns a string representation of the CustomerOrder object.
     *
     * @return a string containing the order details
     */
    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                ", bakeryItems=" + bakeryItems.size() + " items" +
                ", customer=" + customer.getName() +
                '}';
    }

}
