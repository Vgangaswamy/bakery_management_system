package edu.iit.sat.itmd4515.vgangaswamy.lab3;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * A class representing the Language entity.
 */
public class Language {
    @NotNull(message = "Language ID cannot be null")
    private Integer languageId;

    @Size(max = 20, message = "Name must be at most 20 characters")
    private String name;

    /**
     * Default constructor
     */
    public Language() {
    }

    // Constructor with all fields
    public Language(Integer language_id, String name) {
        this.languageId = language_id;
        this.name = name;
    }

    /**
     * Get the value of the language_id
     * @return language_id
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * Set the value of the language_id
     * @param language_id
     */
    public void setLanguageId(Integer language_id) {
        this.languageId = language_id;
    }

    /**
     * Get the value of the Name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of the Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", name='" + name + '\'' +
                '}';
    }
}
