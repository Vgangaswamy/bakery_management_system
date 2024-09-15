package domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


/**
 *
 */
public class Language {
    @NotNull
    private Integer language_id;
    @Size(max = 20, message = "Name must be at most 20 characters")
    private String name;


    /**
     *
     */
    public Language() {
    }

    // Constructor with all fields
    public Language(Integer language_id, String name) {
        this.language_id = language_id;
        this.name = name;

    }

    /**
     * Get the value of the id
     * @return
     */
    public int getLanguageId() {
        return language_id;
    }

    /**
     * Set the value of the id
     * @param language_id
     */
    public void setLanguageId(int language_id) {
        this.language_id = language_id;
    }

    /**
     * Get the value of the Name
     * @return
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
                "languageid=" + language_id +
                ", name='" + name +
                '}';
    }


}
