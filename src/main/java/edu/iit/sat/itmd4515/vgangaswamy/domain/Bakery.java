package edu.iit.sat.itmd4515.vgangaswamy.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Bakery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "product_name")
    private String name;

    private String product_description;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    // Default constructor (required by JPA)
    public Bakery() {
    }

    // Constructor that accepts 'name', 'product_description', and 'ProductType'
    public Bakery(String name, String product_description, ProductType type) {
        this.name = name;
        this.product_description = product_description;
        this.type = type;
    }

    // Constructor for 'name' and 'product_description'
    public Bakery(String name, String product_description) {
        this.name = name;
        this.product_description = product_description;
    }

    // Constructor for 'id' and 'name'
    public Bakery(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Constructor for 'product_description'
    public Bakery(String product_description) {
        this.product_description = product_description;
    }

    // Constructor for 'ProductType'
    public Bakery(ProductType type) {
        this.type = type;
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

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bakery)) return false;
        Bakery bakery = (Bakery) o;
        if (this.id == null || bakery.id == null) {
            return false;
        }
        return Objects.equals(getId(), bakery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    // toString
    @Override
    public String toString() {
        return "Bakery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", product_description='" + product_description + '\'' +
                ", type=" + type +
                '}';
    }
}
