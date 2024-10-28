package edu.iit.sat.itmd4515.vgangaswamy.service;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Customer;
import edu.iit.sat.itmd4515.vgangaswamy.domain.Ingredient;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class IngredientService extends AbstractService<Ingredient> {
    public IngredientService() {
        super(Ingredient.class);
    }
    public List<Ingredient> readAll() {
        return super.readAll("Ingredient.readAll");
    }
}
