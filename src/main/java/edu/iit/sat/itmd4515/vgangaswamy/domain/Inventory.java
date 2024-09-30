package edu.iit.sat.itmd4515.vgangaswamy.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Min(0)
    private int stockLevel;
    @Min(0)
    private int minimumStockLevel;

    @OneToOne(cascade = CascadeType.ALL)
    private Ingredient ingredient;



    public Inventory(int stockLevel, int minimumStockLevel, Ingredient ingredient) {
        this.stockLevel = stockLevel;
        this.minimumStockLevel = minimumStockLevel;
        this.ingredient = ingredient;
    }
    public Inventory(String flourStock, int stockLevel, int minimumStockLevel, Ingredient ingredient) {
        this.name = flourStock; // Set the name here
        this.stockLevel = stockLevel;
        this.minimumStockLevel = minimumStockLevel;
        this.ingredient = ingredient;
    }


    public Inventory() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public void setMinimumStockLevel(int minimumStockLevel) {
        this.minimumStockLevel = minimumStockLevel;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        if (ingredient != null) {
            ingredient.setInventory(this); // Ensure bidirectional relationship is maintained.
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;
        Inventory inventory = (Inventory) o;
        if(this.id == null || inventory.id == null) {
            return false;
        }
        return Objects.equals(getId(), inventory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +  // Corrected: added quotes around name
                ", stockLevel=" + stockLevel +
                ", minimumStockLevel=" + minimumStockLevel +
                ", ingredient=" + (ingredient != null ? ingredient.getName() : "null") +
                '}';
    }
}
