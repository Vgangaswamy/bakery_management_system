package edu.iit.sat.itmd4515.vgangaswamy.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "Bakery.readAll", query = "select b from Bakery b")
public class Bakery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, name = "product_name")
    private String name;

    @Column(name = "product_description")
    @NotBlank
    private String productDescription;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Min(1)
    private float price;

    @Min(1)
    private int quantity;

    @Column(nullable = false)
    private boolean isAvailable;

    /**
     * ManyToMany bidirectional relationship
     * Bakery is the inverse side
     * CustomerOrder is the owning side
     */
    @ManyToMany(mappedBy = "bakeryItems")
    private List<CustomerOrder> customerOrders = new ArrayList<>();


    public Bakery() {}

    public Bakery(String name, String productDescription, ProductType type, float price, int quantity, boolean isAvailable) {
        this.name = name;
        this.productDescription = productDescription;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public void addCustomerOrders(CustomerOrder customerOrders) {
        if(!this.customerOrders.contains(customerOrders)) {
            this.customerOrders.add(customerOrders);
        }
        if(!customerOrders.getBakeryItems().contains(this))  {
            customerOrders.getBakeryItems().add(this);
        }
    }
    public void removeCustomerOrders(CustomerOrder customerOrders) {
        if(!this.customerOrders.contains(customerOrders)) {
            this.customerOrders.remove(customerOrders);
        }
        if(!customerOrders.getBakeryItems().contains(this))  {
            customerOrders.getBakeryItems().remove(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bakery)) return false;
        Bakery bakery = (Bakery) o;
        if(this.id == null || bakery.id == null) {
            return false;
        }
        return Objects.equals(getId(), bakery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Bakery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", quantity=" + quantity +
                ", isAvailable=" + isAvailable +
                ", customerOrders=" + customerOrders.size() + " orders" +
                '}';
    }

}
