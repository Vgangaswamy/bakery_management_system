package edu.iit.sat.itmd4515.vgangaswamy.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

/**
 * The Ingredient class represents an ingredient used in bakery products.
 * It includes attributes such as the ingredient's ID, name, and quantity.
 */
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Min(1)
    private int quantity;

    /**
     * OneToOne unidirectional relationship
     * Ingredient is the owning (only) side
     */
    @OneToOne(mappedBy = "ingredient")
    private Inventory inventory;

    /**
     * Default constructor (required by JPA).
     */
    public Ingredient() {
    }

    /**
     * Gets the ID of the ingredient.
     *
     * @return the ingredient's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the ingredient.
     *
     * @param id the ingredient's ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the ingredient.
     *
     * @return the ingredient's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the ingredient.
     *
     * @param name the ingredient's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the quantity of the ingredient.
     *
     * @return the ingredient's quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ingredient.
     *
     * @param quantity the ingredient's quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        if (this.id == null || that.id == null) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Returns a string representation of the Ingredient object.
     *
     * @return a string containing the ingredient details
     */
    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", inventory=" + (inventory != null ? inventory.getId() : "null") +
                '}';
    }
}
