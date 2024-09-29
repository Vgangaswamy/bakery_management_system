package edu.iit.sat.itmd4515.vgangaswamy.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stockLevel;
    private int minimumStockLevel;

    /**
     * OneToOne unidirectional relationship
     * Inventory is the owning (only) side
     */
    @OneToOne(optional = true)
    private Ingredient ingredient;

    // Constructors
    public Inventory() {
    }

    public Inventory(int stockLevel, int minimumStockLevel, Ingredient ingredient) {
        this.stockLevel = stockLevel;
        this.minimumStockLevel = minimumStockLevel;
        this.ingredient = ingredient;
    }

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
                ", stockLevel=" + stockLevel +
                ", minimumStockLevel=" + minimumStockLevel +
                ", ingredient=" + (ingredient != null ? ingredient.getName() : "null") +
                '}';
    }
}
