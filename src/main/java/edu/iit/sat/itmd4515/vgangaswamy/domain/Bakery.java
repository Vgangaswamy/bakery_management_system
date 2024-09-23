package edu.iit.sat.itmd4515.vgangaswamy.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Bakery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, name = "product_name")
    private String name;

    private String product_description;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Min(0)
    private float price;

    @Min(0)
    private int quantity;

    @Column(nullable = false)
    private boolean isAvailable;

    // Default constructor (required by JPA)
    public Bakery() {
    }

    // Constructor that accepts 'name', 'product_description', and 'ProductType'
    public Bakery(String name, String product_description, ProductType type, float price, int quantity, boolean isAvailable) {
        this.name = name;
        this.product_description = product_description;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
    }

    // Getter and Setter for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for 'product_description'
    public String getProductDescription() {
        return product_description;
    }

    public void setProductDescription(String product_description) {
        this.product_description = product_description;
    }

    // Getter and Setter for 'ProductType'
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

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bakery)) return false;
        Bakery bakery = (Bakery) o;
        return id != null && id.equals(bakery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "Bakery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", product_description='" + product_description + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", quantity=" + quantity +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
